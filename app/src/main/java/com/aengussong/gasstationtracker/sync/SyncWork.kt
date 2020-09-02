package com.aengussong.gasstationtracker.sync

import android.content.Context
import android.widget.Toast
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.aengussong.gasstationtracker.repo.local.KEY_STATIONS
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class SyncWork(private val appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
        suspendCoroutine<Result> { continuation ->
            FirebaseDatabase.getInstance().apply {
                goOnline()
                reference.child(KEY_STATIONS)
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onCancelled(error: DatabaseError) {
                            println("GAS_STATION_TRACKER: on cancelled")
                            continuation.resume(Result.failure())
                        }

                        override fun onDataChange(snapshot: DataSnapshot) {
                            println("GAS_STATION_TRACKER: Data synced")
                            Toast.makeText(appContext, "Database synced", Toast.LENGTH_SHORT).show()
                            continuation.resume(Result.success())
                        }
                    })
            }
        }
    }
}