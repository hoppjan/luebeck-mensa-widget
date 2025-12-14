package de.janhopp.luebeckmensawidget

import android.content.Context
import android.util.Log
import androidx.work.BackoffPolicy
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import de.janhopp.luebeckmensawidget.api.MensaApi
import de.janhopp.luebeckmensawidget.storage.MenuStorage
import de.janhopp.luebeckmensawidget.storage.OptionsStorage
import de.janhopp.luebeckmensawidget.utils.buildConstraints
import de.janhopp.luebeckmensawidget.widget.MensaWidget
import de.janhopp.luebeckmensawidget.widget.getWidgetConfig
import kotlin.time.Duration.Companion.hours
import kotlin.time.toJavaDuration

class CompleteSyncWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        Log.d("CompleteSyncWorker", "doWork")
        val options = OptionsStorage(applicationContext)
        val config = options.getWidgetConfig()
        val meals = MensaApi().getAllDaysMeals(config.locations)
        Log.d("CompleteSyncWorker", "doWork: $meals")
        val storage = MenuStorage(applicationContext)
        if (meals.isEmpty()) return Result.retry()
        storage.setMensaDays(meals)
        Log.d("CompleteSyncWorker", "doWork: success")
        MensaWidget.updateAll(applicationContext)
        return Result.success()
    }

    companion object {
        val syncWorkRequest: PeriodicWorkRequest =
            PeriodicWorkRequestBuilder<CompleteSyncWorker>(repeatInterval = 12.hours.toJavaDuration())
                .setConstraints(
                    buildConstraints {
                        setRequiredNetworkType(NetworkType.UNMETERED)
                        setRequiresBatteryNotLow(true)
                        setRequiresCharging(true)
                    }
                )
                .setBackoffCriteria(BackoffPolicy.LINEAR, duration = 2.hours.toJavaDuration())
                .build()

        fun Context.enqueueCompleteSyncWork() {
            Log.d("CompleteSyncWorker", "enqueueSyncWork")
            WorkManager.getInstance(applicationContext)
                .enqueueUniquePeriodicWork(
                    CompleteSyncWorker::class.java.name,
                    ExistingPeriodicWorkPolicy.UPDATE,
                    syncWorkRequest.also { Log.d("CompleteSyncWorker", "enqueueSyncWork: $it") }
                )
        }
    }
}
