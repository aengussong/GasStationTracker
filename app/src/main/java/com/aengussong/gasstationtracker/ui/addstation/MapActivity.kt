package com.aengussong.gasstationtracker.ui.addstation

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.aengussong.gasstationtracker.R
import com.aengussong.gasstationtracker.utils.doOnApplyWindowInsets
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_map.*

private const val REQUEST_FINE_LOCATION = 4050
private const val DEFAULT_ZOOM = 18.0f

/**
 * Activity, that contains SupportMapFragment, checks fine location permission and centers on
 * the last known user position.
 * */
abstract class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private var mMap: GoogleMap? = null
    private lateinit var locationProvider: FusedLocationProviderClient

    /**
     * Main method to be overridden while working with [MapActivity]. Overriding other functions is
     * not advisable. Called after [onMapReady] callback is called and user's last known location
     * is initialized.
     * */
    abstract fun onMapLoaded(googleMap: GoogleMap)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        locationProvider = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        setUpMapInsets()
        getLocationPermission()
        onMapLoaded(googleMap)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_FINE_LOCATION -> {
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    displayLocation()
                }
            }
        }
    }

    /**
     * Adds paddings to [mMap] based on system insets, so status bar and navigation bar wouldn't
     * overlap map
     * */
    private fun setUpMapInsets() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            map_root.doOnApplyWindowInsets { _, insets, _ ->
                mMap?.setPadding(0, insets.systemWindowInsetBottom, 0, insets.systemWindowInsetTop)
                insets
            }
        }
    }

    private fun getLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            displayLocation()
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_FINE_LOCATION
            )
        }
    }

    private fun displayLocation() {
        try {
            mMap?.isMyLocationEnabled = true
            mMap?.uiSettings?.isMyLocationButtonEnabled = true
            getDeviceLocation()
        } catch (ex: SecurityException) {
            Log.e(this::class.simpleName, "Exception displaying location", ex)
        }
    }

    @SuppressLint("MissingPermission")
    private fun getDeviceLocation() {
        locationProvider.lastLocation.addOnCompleteListener(this) { task ->
            if (!task.isSuccessful) return@addOnCompleteListener

            task.result?.let {
                mMap?.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        LatLng(it.latitude, it.longitude),
                        DEFAULT_ZOOM
                    )
                )
            }

        }
    }
}