package com.aengussong.gasstationtracker.ui.addstation

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.aengussong.gasstationtracker.R
import com.aengussong.gasstationtracker.ui.addstation.dialog.DetailsDialog
import com.aengussong.gasstationtracker.utils.AddressProvider
import com.google.android.gms.maps.GoogleMap


class AddStationActivity : MapActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, AddStationActivity::class.java)
        }
    }

    override fun onMapLoaded(googleMap: GoogleMap) {
        Toast.makeText(this, R.string.add_station_explanation, Toast.LENGTH_LONG).show()
        googleMap.setOnMapLongClickListener { latLng ->
            val address = AddressProvider.fromLatLng(this@AddStationActivity, latLng)
            DetailsDialog(address).show(supportFragmentManager, DetailsDialog::class.simpleName)
        }
    }
}