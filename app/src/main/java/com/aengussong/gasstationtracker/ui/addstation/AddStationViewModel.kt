package com.aengussong.gasstationtracker.ui.addstation

import androidx.lifecycle.*
import com.aengussong.gasstationtracker.model.Station
import com.aengussong.gasstationtracker.repo.Repository
import kotlinx.coroutines.launch

class AddStationViewModel(private val repo: Repository) : ViewModel() {

    val shouldGoBack: LiveData<Boolean>
        get() = _shouldGoBack
    private val _shouldGoBack = MutableLiveData<Boolean>()

    fun addStation(station: Station) {
        viewModelScope.launch {
            repo.addStation(station)
        }
        _shouldGoBack.value = true
    }

    fun updateStation(station: Station) {
        viewModelScope.launch {
            repo.updateStation(station)
        }
        _shouldGoBack.value = true
    }

    fun getStation(id: Int): LiveData<Station?> {
        return repo.getStation(id).asLiveData()
    }
}