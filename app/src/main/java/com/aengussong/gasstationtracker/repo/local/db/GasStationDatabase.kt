package com.aengussong.gasstationtracker.repo.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aengussong.gasstationtracker.model.Station
import com.aengussong.gasstationtracker.repo.local.dao.StationDao


@Database(entities = [Station::class], version = 1)
abstract class GasStationDatabase : RoomDatabase() {
    abstract fun stationDao(): StationDao
}