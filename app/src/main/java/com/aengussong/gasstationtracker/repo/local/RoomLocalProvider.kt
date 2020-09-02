package com.aengussong.gasstationtracker.repo.local

import com.aengussong.gasstationtracker.model.Station
import com.aengussong.gasstationtracker.repo.local.dao.StationDao
import kotlinx.coroutines.flow.Flow

class RoomLocalProvider(private val stationDao: StationDao) : LocalDataProvider {

    override suspend fun saveStation(station: Station) {
        stationDao.addStation(station)
    }

    override fun getStations(): Flow<List<Station>> {
        return stationDao.getStations()
    }

    override suspend fun updateStation(station: Station) {
        stationDao.updateStation(station)
    }

    override fun getStation(id: Int): Flow<Station?> {
        return stationDao.getStation(id)
    }

    override suspend fun deleteStation(id: Int) {
        stationDao.deleteStation(id)
    }


}