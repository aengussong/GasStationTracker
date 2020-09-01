package com.aengussong.gasstationtracker.model

data class Station(
    val address: String,
    val fuelSupplier: String,
    val type: String,
    val quantity: Int,
    val cost: Double,
    val visitedCounter: Int = 0
)