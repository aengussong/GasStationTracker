package com.aengussong.gasstationtracker.ui.main.stats

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aengussong.gasstationtracker.model.Station
import com.aengussong.gasstationtracker.repo.Repository
import ir.androidexception.datatable.model.DataTableHeader
import ir.androidexception.datatable.model.DataTableRow
import kotlinx.coroutines.flow.map

class StationsStatsViewModel(private val repo: Repository) : ViewModel() {

    private val headers = DataTableHeader.Builder()
        .item("Id", 1)
        .item("Supplier", 5)
        .item("Cost", 2)
        .build()

    fun getStations(): LiveData<StationsDto> {
        return repo.getStations().map {
            mapToDto(it.filterNotNull())
        }.asLiveData()
    }

    private fun mapToDto(stations: List<Station>): StationsDto {
        val rows = ArrayList<DataTableRow>()
        stations.forEach { station ->
            val row = DataTableRow.Builder()
                .value(station.id)
                .value(station.fuelSupplier)
                .value(station.cost.toString())
                .build()
            rows.add(row)
        }

        return StationsDto(headers, rows)
    }
}

data class StationsDto(val header: DataTableHeader, val rows: ArrayList<DataTableRow>)