//package com.example.ticketbookingapp.Domain
//
//import java.io.Serializable
//
//data class BookingModel(
//    val flightId: String = "",
//    val date: String = "",
//    val from: String = "",
//    val to: String = "",
//    val typeClass: String = "",
//    val seats: String = "",
//    val price: Double = 0.0,
//    val bookingDate: String = ""
//) : Serializable

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
    val bookingDate: String = "",
    val airlineName: String = "", // Thêm trường
    val airlineLogo: String = "", // Thêm trường
    val arriveTime: String = "", // Thêm trường
    val fromShort: String = "", // Thêm trường
    val toShort: String = "", // Thêm trường
    val time: String = "", // Thêm trường
    val classSeat: String = "" // Thêm trường
) : Serializable