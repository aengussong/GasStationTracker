package com.aengussong.gasstationtracker.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.aengussong.gasstationtracker.R
import com.aengussong.gasstationtracker.ui.addstation.AddStationActivity
import com.aengussong.gasstationtracker.ui.main.adapter.StationsListAdapter
import com.aengussong.gasstationtracker.utils.doWhileActive
import kotlinx.android.synthetic.main.fragment_stations_list.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class StationsListFragment : Fragment(R.layout.fragment_stations_list) {

    private val viewModel: MainViewModel by viewModel()

    @ExperimentalCoroutinesApi
    @FlowPreview
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = StationsListAdapter()
        stations_list.adapter = adapter

        lifecycleScope.doWhileActive {
            adapter.getEditFlow().collect {
                openAddStationScreen(it.id)
            }
        }

        lifecycleScope.doWhileActive {
            adapter.getDeleteFlow().collect {
                viewModel.deleteStation(it.id)
            }
        }

        viewModel.getStations().observe(viewLifecycleOwner, Observer {
            adapter.updateStations(it)
        })

        fab.setOnClickListener {
            openAddStationScreen()
        }
    }

    private fun openAddStationScreen(stationId: Int? = null) {
        startActivity(AddStationActivity.getIntent(requireContext(), stationId))
    }

}