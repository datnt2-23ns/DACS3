package com.example.ticketbookingapp.Domain

import java.io.Serializable

data class BookingModel(
    val flightId: String = "",
    val date: String = "",
    val from: String = "",
    val to: String = "",
    val typeClass: String = "",
    val seats: String = "",
    val price: Double = 0.0,
    val bookingDate: String = ""
) : Serializable