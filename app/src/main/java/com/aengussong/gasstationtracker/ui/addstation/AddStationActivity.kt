package com.aengussong.gasstationtracker.ui.addstation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.aengussong.gasstationtracker.R
import com.aengussong.gasstationtracker.model.Station
import com.aengussong.gasstationtracker.ui.addstation.dialog.DetailsDialog
import com.aengussong.gasstationtracker.utils.AddressProvider
import com.google.android.gms.maps.GoogleMap
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val EXTRA_ID = "extra_id"

class AddStationActivity : MapActivity() {

    companion object {
        fun getIntent(context: Context, stationId: String?): Intent {
            return Intent(context, AddStationActivity::class.java).also { intent ->
                stationId?.let { id -> intent.putExtra(EXTRA_ID, id) }
            }
        }
    }

    private val viewModel: AddStationViewModel by viewModel()

    private val stationId: String? by lazy { intent.getStringExtra(EXTRA_ID) }
    private var station: Station? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.shouldGoBack.observe(this, Observer {
            onBackPressed()
        })

        stationId?.let {
            viewModel.getStation(it).observe(this@AddStationActivity, Observer { station ->
                this.station = station
                DetailsDialog(station).show(supportFragmentManager, DetailsDialog::class.simpleName)
            })
        }
    }

    override fun onMapLoaded(googleMap: GoogleMap) {
        Toast.makeText(this, R.string.map_explanation, Toast.LENGTH_LONG).show()
        googleMap.setOnMapLongClickListener { latLng ->
            val address = AddressProvider.fromLatLng(this@AddStationActivity, latLng)
            val dialog = station?.let { station ->
                DetailsDialog(station.copy(address = address))
            } ?: DetailsDialog(address)

            dialog.show(supportFragmentManager, DetailsDialog::class.simpleName)
        }
    }
}