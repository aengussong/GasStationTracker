package com.aengussong.gasstationtracker.repo.local

import com.aengussong.gasstationtracker.model.Station
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.*

const val KEY_STATIONS = "stations"

class FirebaseDataProvider(db: DatabaseReference) : LocalDataProvider {

    private val stationsDb = db.child(KEY_STATIONS)

    override suspend fun saveStation(station: Station) {
        stationsDb.apply {
            val key = push().key ?: UUID.randomUUID().toString()
            val stationWithId = station.copy(id = key)
            child(key).setValue(stationWithId)
        }
    }

    @ExperimentalCoroutinesApi
    override fun getStations(): Flow<List<Station?>> {
        return callbackFlow {
            val listener = object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    println("cancelled getStations: $error")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    offer(snapshot.children.map { it.getValue(Station::class.java) })
                }
            }
            stationsDb.addValueEventListener(listener)

            awaitClose {
                stationsDb.removeEventListener(listener)
            }
        }
    }

    override suspend fun updateStation(station: Station) {
        stationsDb.child(station.id).setValue(station)
    }

    @ExperimentalCoroutinesApi
    override fun getStation(id: String): Flow<Station?> {
        return callbackFlow {
            val listener = object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    println("cancelled $id: $error")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    offer(snapshot.getValue(Station::class.java))
                }
            }
            stationsDb.child(id).addListenerForSingleValueEvent(listener)

            awaitClose {
                stationsDb.child(id).removeEventListener(listener)
            }
        }
    }

    override suspend fun deleteStation(id: String) {
        stationsDb.child(id).removeValue()
    }

}