package com.aengussong.gasstationtracker.model

data class Station(
    val id: Int = 0,
    val address: String,
    val fuelSupplier: String,
    val type: String,
    val quantity: Int,
    val cost: Double,
    val visitedCounter: Int = 0
)