package com.aengussong.gasstationtracker.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.aengussong.gasstationtracker.model.Station
import com.aengussong.gasstationtracker.repo.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repo: Repository) : ViewModel() {

    fun getStations(): LiveData<List<Station>> {
        return repo.getStations().asLiveData()
    }

    fun deleteStation(id: Int) {
        viewModelScope.launch {
            repo.deleteStation(id)
        }
    }
}