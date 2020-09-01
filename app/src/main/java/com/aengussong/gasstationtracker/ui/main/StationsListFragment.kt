package com.aengussong.gasstationtracker.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.aengussong.gasstationtracker.R
import com.aengussong.gasstationtracker.ui.addstation.AddStationActivity
import kotlinx.android.synthetic.main.fragment_stations_list.*

class StationsListFragment : Fragment(R.layout.fragment_stations_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fab.setOnClickListener {
            startActivity(AddStationActivity.getIntent(requireContext()))
        }
    }

}