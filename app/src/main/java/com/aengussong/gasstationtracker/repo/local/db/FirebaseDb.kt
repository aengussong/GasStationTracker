package com.aengussong.gasstationtracker.repo.local.db

import com.aengussong.gasstationtracker.sync.SyncManager
import com.google.firebase.database.*

class FirebaseDb {
    companion object {
        fun getInstance(syncManager: SyncManager, userId: String): DatabaseReference {
            return FirebaseDatabase.getInstance().run {
                setPersistenceEnabled(true)
                setOfflineListener(syncManager, this)
                getReference(userId)
            }
        }

        private fun setOfflineListener(syncManager: SyncManager, db: FirebaseDatabase) {
            val connectedRef = db.getReference(".info/connected")
            connectedRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val connected = snapshot.getValue(Boolean::class.java) ?: false
                    if (connected) {
                        syncManager.cancelWork()
                    } else {
                        syncManager.setUpSyncWork()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    println("Offline listener was cancelled")
                }
            })
        }
    }
}