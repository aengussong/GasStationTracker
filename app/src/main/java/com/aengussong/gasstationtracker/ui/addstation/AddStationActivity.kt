package com.aengussong.gasstationtracker.ui.addstation

import android.content.Context
import android.content.Intent
import com.google.android.gms.maps.GoogleMap

class AddStationActivity : MapActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, AddStationActivity::class.java)
        }
    }

    override fun onMapLoaded(googleMap: GoogleMap) {
        googleMap.setOnMapLongClickListener { latLng ->

        }
    }
}