package com.aengussong.gasstationtracker.repo.local

import com.aengussong.gasstationtracker.model.Station
import kotlinx.coroutines.flow.Flow

interface LocalDataProvider {

    suspend fun saveStation(station: Station)

    fun getStations(): Flow<List<Station>>

    suspend fun updateStation(station: Station)

    fun getStation(id: Int): Flow<Station?>

    suspend fun deleteStation(id: Int)
}