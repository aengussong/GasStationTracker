package com.aengussong.gasstationtracker.ui.addstation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aengussong.gasstationtracker.model.Station

class AddStationViewModel : ViewModel() {

    val shouldGoBack: LiveData<Boolean>
        get() = shouldGoBack
    private val _shouldGoBack = MutableLiveData<Boolean>()

    fun addStation(station: Station) {

        _shouldGoBack.value = true
    }
}