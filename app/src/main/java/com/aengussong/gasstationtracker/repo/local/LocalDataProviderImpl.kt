package com.aengussong.gasstationtracker.repo.local

import com.aengussong.gasstationtracker.model.Station
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow

@FlowPreview
@ExperimentalCoroutinesApi
class LocalDataProviderImpl : LocalDataProvider {

    private val stations = mutableListOf<Station>()

    private val channel = ConflatedBroadcastChannel<List<Station>>()

    private var idTracker = 0

    override suspend fun saveStation(station: Station) {
        stations.add(station.copy(id = ++idTracker))
        channel.send(stations)
    }

    override fun getStations(): Flow<List<Station>> {
        return channel.asFlow()
    }

    override suspend fun updateStation(station: Station) {
        val index = stations.indexOfFirst { it.id == station.id }
        stations[index] = station

        channel.send(stations)
    }

    override fun getStation(id: Int): Flow<Station?> {
        return flow {
            emit(stations.find { it.id == id })
        }
    }

    override suspend fun deleteStation(id: Int) {
        val index = stations.find { it.id == id }
        stations.remove(index)

        channel.send(stations)
    }
}