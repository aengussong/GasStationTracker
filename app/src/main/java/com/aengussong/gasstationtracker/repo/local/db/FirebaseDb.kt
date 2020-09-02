package com.aengussong.gasstationtracker.repo.local.db

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseDb {
    companion object {
        fun getInstance(userId: String): DatabaseReference {
            return FirebaseDatabase.getInstance().run {
                setPersistenceEnabled(true)
                getReference(userId)
            }
        }
    }
}