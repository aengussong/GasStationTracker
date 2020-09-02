package com.aengussong.gasstationtracker.sync

import android.content.Context
import androidx.work.*

class SyncManager(private val appContext: Context) {
    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()


    private val worker = OneTimeWorkRequestBuilder<SyncWork>()
        .setConstraints(constraints)
        .build()

    fun setUpSyncWork() {
        WorkManager.getInstance(appContext)
            .enqueueUniqueWork("syncWork", ExistingWorkPolicy.REPLACE, worker)
    }

    fun cancelWork() {
        WorkManager.getInstance(appContext).getWorkInfoById(worker.id)
    }
}