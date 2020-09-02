package com.aengussong.gasstationtracker.model

data class Station(
    val id: String = "",
    val address: String,
    val fuelSupplier: String,
    val type: String,
    val quantity: Int,
    val cost: Double
) {
    //secondary non parameter constructor to parse response from Realtime Database
    constructor() : this("", "", "", "", 0, .0)
}