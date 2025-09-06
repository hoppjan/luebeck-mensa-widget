package de.janhopp.luebeckmensawidget

import android.content.Context
import android.util.Log
import androidx.work.BackoffPolicy
import androidx.work.Constraints
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
import de.janhopp.luebeckmensawidget.widget.MensaWidget
import de.janhopp.luebeckmensawidget.widget.getWidgetConfig
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.toJavaDuration

class SyncWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        Log.d("SyncWorker", "doWork")
        val options = OptionsStorage(applicationContext)
        val config = options.getWidgetConfig()
        val mealsToday = MensaApi().getMealsToday(config.locations)
        Log.d("SyncWorker", "doWork: $mealsToday")
        val storage = MenuStorage(applicationContext)
        if (mealsToday == null) return Result.retry()
        storage.setMensaDays(listOf(mealsToday))
        Log.d("SyncWorker", "doWork: success")
        MensaWidget.updateAll(applicationContext)
        return Result.success()
    }

    companion object {
        val syncWorkRequest: PeriodicWorkRequest =
            PeriodicWorkRequestBuilder<SyncWorker>(repeatInterval = 1.hours.toJavaDuration())
                .setConstraints(
                    buildConstraints {
                        setRequiredNetworkType(NetworkType.CONNECTED)
                        setRequiresBatteryNotLow(true)
                    }
                )
                .setBackoffCriteria(BackoffPolicy.LINEAR, duration = 30.minutes.toJavaDuration())
                .build()

        fun Context.enqueueSyncWork() {
            Log.d("SyncWorker", "enqueueSyncWork")
            WorkManager.getInstance(applicationContext)
                .enqueueUniquePeriodicWork(
                    SyncWorker::class.java.name,
                    ExistingPeriodicWorkPolicy.UPDATE,
                    syncWorkRequest.also { Log.d("SyncWorker", "enqueueSyncWork: $it") }
                )
        }
    }
}

private fun buildConstraints(builder: Constraints.Builder.() -> Unit): Constraints =
    Constraints.Builder().apply(builder).build()
