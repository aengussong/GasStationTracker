package com.aengussong.gasstationtracker.ui.main.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aengussong.gasstationtracker.R
import com.aengussong.gasstationtracker.model.Station
import com.aengussong.gasstationtracker.utils.DefaultDiffCallback
import com.aengussong.gasstationtracker.utils.sendAsync
import kotlinx.android.synthetic.main.item_station.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

@FlowPreview
@ExperimentalCoroutinesApi
class StationsListAdapter : RecyclerView.Adapter<StationsListAdapter.StationViewHolder>() {

    private val editChannel = ConflatedBroadcastChannel<Station>()
    private val deleteChannel = ConflatedBroadcastChannel<Station>()

    private val list = mutableListOf<Station>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_station, parent, false)
        return StationViewHolder(view).apply {
            with(itemView) {
                iv_delete.setOnClickListener { v ->
                    v.sendAsync(
                        deleteChannel,
                        list[adapterPosition]
                    )
                }
                iv_edit.setOnClickListener { v -> v.sendAsync(editChannel, list[adapterPosition]) }
            }
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun updateStations(stationsList: List<Station?>) {
        val callback = DefaultDiffCallback(list, stationsList)
        DiffUtil.calculateDiff(callback).dispatchUpdatesTo(this)

        list.clear()
        list.addAll(stationsList.filterNotNull())
    }

    fun getEditFlow(): Flow<Station> = editChannel.asFlow()

    fun getDeleteFlow(): Flow<Station> = deleteChannel.asFlow()

    inner class StationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(station: Station) {
            with(itemView) {
                station_address.text = station.address
                station_supplier.text = station.fuelSupplier
            }
        }
    }
}