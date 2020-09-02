package com.aengussong.gasstationtracker.repo.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.aengussong.gasstationtracker.model.Station
import kotlinx.coroutines.flow.Flow

@Dao
interface StationDao {

    @Insert
    suspend fun addStation(station: Station)

    @Query("SELECT * FROM station")
    fun getStations(): Flow<List<Station>>

    @Update
    suspend fun updateStation(station: Station)

    @Query("SELECT * FROM station WHERE id=:id")
    fun getStation(id: Int): Flow<Station?>

    @Query("DELETE FROM station WHERE id=:id")
    suspend fun deleteStation(id: Int)
}