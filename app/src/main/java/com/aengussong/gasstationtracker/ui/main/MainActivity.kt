package com.aengussong.gasstationtracker.ui.main

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.aengussong.gasstationtracker.R
import com.aengussong.gasstationtracker.ui.main.adapter.MainAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pager.adapter = MainAdapter(this)

        TabLayoutMediator(tab_layout, pager) { tab, position ->
            when (position) {
                0 -> R.string.stations_list
                1 -> R.string.stations_stats
                else -> throw IllegalArgumentException()
            }.also { tab.setText(it) }
        }.attach()
    }
}