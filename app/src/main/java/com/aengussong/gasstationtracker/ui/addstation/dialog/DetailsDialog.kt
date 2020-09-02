package com.aengussong.gasstationtracker.ui.addstation.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.aengussong.gasstationtracker.R
import com.aengussong.gasstationtracker.model.Station
import com.aengussong.gasstationtracker.ui.addstation.AddStationViewModel
import com.aengussong.gasstationtracker.utils.getString
import kotlinx.android.synthetic.main.dialog_add_details.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailsDialog private constructor() : DialogFragment() {

    private var station: Station? = null
    private var address: String? = null

    constructor(station: Station?) : this() {
        this.station = station
    }

    constructor(address: String?) : this() {
        this.address = address
    }

    private val viewModel: AddStationViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_add_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        when {
            station != null -> editStation()
            address != null -> addStation()
            else -> throw IllegalArgumentException(
                "${this::class.simpleName} can work only with provided station or address"
            )
        }
    }

    private fun editStation() {
        station?.apply {
            et_address.setText(address)
            et_fuel_supplier.setText(fuelSupplier)
            et_type.setText(type)
            et_quantity.setText(quantity.toString())
            et_cost.setText(cost.toString())
        }

        btn_add.setOnClickListener {
            val updatedStation = getStation().copy(id = station?.id ?: "")
            viewModel.updateStation(updatedStation)
            dismiss()
        }
    }

    private fun addStation() {
        et_address.setText(address)
        btn_add.setOnClickListener {
            viewModel.addStation(getStation())
            dismiss()
        }
    }

    private fun getStation(): Station {
        return Station(
            address = et_address.getString(),
            fuelSupplier = et_fuel_supplier.getString(),
            type = et_type.getString(),
            quantity = et_quantity.getString().toIntOrNull() ?: 0,
            cost = et_cost.getString().toDoubleOrNull() ?: 0.0
        )
    }
}