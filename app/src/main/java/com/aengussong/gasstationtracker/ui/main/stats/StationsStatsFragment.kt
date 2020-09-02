package com.aengussong.gasstationtracker.ui.main.stats

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.aengussong.gasstationtracker.R
import kotlinx.android.synthetic.main.fragment_stations_stats.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class StationsStatsFragment : Fragment(R.layout.fragment_stations_stats) {

    private val viewModel: StationsStatsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getStations().observe(viewLifecycleOwner, Observer { dto ->
            data_table.header = dto.header
            data_table.rows = dto.rows
            data_table.inflate(requireContext())
        })
    }
}