package de.janhopp.luebeckmensawidget

import android.content.Context
import android.util.Log
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.toJavaDuration

class SyncWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {
        Log.d("SyncWorker", "doWork")
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
