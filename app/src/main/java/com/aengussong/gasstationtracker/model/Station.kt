package com.aengussong.gasstationtracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Station(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val address: String,
    val fuelSupplier: String,
    val type: String,
    val quantity: Int,
    val cost: Double,
    val visitedCounter: Int = 0
)