package com.aengussong.gasstationtracker.repo

import com.aengussong.gasstationtracker.model.Station
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun addStation(station: Station)

    fun getStations(): Flow<List<Station>>

    suspend fun updateStation(station: Station)

    fun getStation(id: Int): Flow<Station?>

    suspend fun deleteStation(id: Int)
}