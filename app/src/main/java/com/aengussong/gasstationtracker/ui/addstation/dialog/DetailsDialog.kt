package com.aengussong.gasstationtracker.ui.addstation.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.aengussong.gasstationtracker.R
import com.aengussong.gasstationtracker.model.Station
import com.aengussong.gasstationtracker.utils.getString
import kotlinx.android.synthetic.main.dialog_add_details.*

class DetailsDialog(private val address: String) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_add_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        et_address.setText(address)
        btn_add.setOnClickListener {
            val station = Station(
                et_address.getString(),
                et_fuel_supplier.getString(),
                et_type.getString(),
                et_quantity.getString().toIntOrNull() ?: 0,
                et_cost.getString().toDoubleOrNull() ?: 0.0
            )
        }
    }
}