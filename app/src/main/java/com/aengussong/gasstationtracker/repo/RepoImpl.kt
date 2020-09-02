package com.aengussong.gasstationtracker.repo

import com.aengussong.gasstationtracker.model.Station
import com.aengussong.gasstationtracker.repo.local.LocalDataProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RepoImpl(private val local: LocalDataProvider) : Repository {

    override suspend fun addStation(station: Station) = withContext(Dispatchers.IO) {
        local.saveStation(station)
    }

    override fun getStations(): Flow<List<Station>> {
        return local.getStations()
    }

    override suspend fun updateStation(station: Station) = withContext(Dispatchers.IO) {
        local.updateStation(station)
    }

    override fun getStation(id: Int): Flow<Station?> {
        return local.getStation(id)
    }

    override suspend fun deleteStation(id: Int) = withContext(Dispatchers.IO) {
        local.deleteStation(id)
    }
}