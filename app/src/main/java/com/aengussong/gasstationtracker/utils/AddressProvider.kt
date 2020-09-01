package com.aengussong.gasstationtracker.utils

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.maps.model.LatLng
import java.util.*

class AddressProvider {
    companion object {
        fun fromLatLng(context: Context, latLng: LatLng): String {
            return Geocoder(context, Locale.getDefault())
                .getFromLocation(latLng.latitude, latLng.longitude, 1)
                .first()
                .getAddressLine(0)

        }
    }
}