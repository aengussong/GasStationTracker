package com.aengussong.gasstationtracker.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.aengussong.gasstationtracker.ui.main.StationsListFragment
import com.aengussong.gasstationtracker.ui.main.StationsStatsFragment

private const val TABS_COUNT = 2

class MainAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = TABS_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> StationsListFragment()
            1 -> StationsStatsFragment()
            else -> throw IllegalArgumentException("${this::class.simpleName} supports only $TABS_COUNT tabs")
        }
    }

}