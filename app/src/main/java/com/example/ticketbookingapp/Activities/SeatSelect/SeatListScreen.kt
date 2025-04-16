//package com.example.ticketbookingapp.Activities.SeatSelect
//
//import android.util.Log
//import android.widget.Toast
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.constraintlayout.compose.ConstraintLayout
//import com.example.ticketbookingapp.Domain.FlightModel
//import com.example.ticketbookingapp.R
//
//enum class SeatStatus {
//    AVAILABLE,
//    SELECTED,
//    UNAVAILABLE,
//    EMPTY
//}
//
//data class Seat(
//    var status: SeatStatus,
//    var name: String
//)
//
//@Composable
//fun SeatListScreen(
//    flight: FlightModel,
//    onBackClick: () -> Unit,
//    onConfirm: (FlightModel, String, Double) -> Unit
//) {
//    val context = LocalContext.current
//    val seatList = remember { mutableStateListOf<Seat>() }
//    val selectedSeatNames = remember { mutableStateListOf<String>() }
//    var seatCount by remember { mutableStateOf(0) }
//    var totalPrice by remember { mutableStateOf(0.0) }
//
//    LaunchedEffect(Unit) {
//        seatList.clear()
//        val seats = generateSeatList(flight)
//        Log.d(
//            "SeatListScreen",
//            "Generated seats: ${seats.size}, ReservedSeats: ${flight.ReservedSeats}"
//        )
//        seatList.addAll(seats)
//        seatCount = selectedSeatNames.size
//        totalPrice = seatCount * flight.Price
//    }
//
//    fun updatePriceAndCount() {
//        seatCount = selectedSeatNames.size
//        totalPrice = seatCount * flight.Price
//    }
//
//    ConstraintLayout(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(colorResource(R.color.darkPurple2))
//    ) {
//        val (topSection, middSection, bottomSection) = createRefs()
//
//        TopSection(
//            modifier = Modifier
//                .constrainAs(topSection) {
//                    top.linkTo(parent.top)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                },
//            onBackClick = onBackClick
//        )
//
//        ConstraintLayout(
//            modifier = Modifier
//                .padding(top = 80.dp)
//                .constrainAs(middSection) {
//                    top.linkTo(parent.top)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                }
//        ) {
//            val (airplane, seatGrid) = createRefs()
//            Image(
//                painter = painterResource(R.drawable.airple_seat),
//                contentDescription = "Airplane layout",
//                modifier = Modifier.constrainAs(airplane) {
//                    top.linkTo(parent.top)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                }
//            )
//
//            if (seatList.isEmpty()) {
//                Text(
//                    text = "No seats available for Business Class",
//                    color = Color.White,
//                    modifier = Modifier
//                        .padding(top = 240.dp)
//                        .constrainAs(seatGrid) {
//                            top.linkTo(parent.top)
//                            start.linkTo(parent.start)
//                            end.linkTo(parent.end)
//                        }
//                )
//            } else {
//                LazyVerticalGrid(
//                    columns = GridCells.Fixed(7),
//                    modifier = Modifier
//                        .padding(top = 200.dp)
//                        .padding(horizontal = 64.dp)
//                        .constrainAs(seatGrid) {
//                            top.linkTo(parent.top)
//                            start.linkTo(airplane.start)
//                            end.linkTo(airplane.end)
//                        }
//                ) {
//                    items(seatList.size) { index ->
//                        val seat = seatList[index]
//                        SeatItem(
//                            seat = seat,
//                            onSeatClick = {
//                                when (seat.status) {
//                                    SeatStatus.AVAILABLE -> {
//                                        seat.status = SeatStatus.SELECTED
//                                        selectedSeatNames.add(seat.name)
//                                    }
//
//                                    SeatStatus.SELECTED -> {
//                                        seat.status = SeatStatus.AVAILABLE
//                                        selectedSeatNames.remove(seat.name)
//                                    }
//
//                                    else -> {}
//                                }
//                                updatePriceAndCount()
//                            }
//                        )
//                    }
//                }
//            }
//        }
//
//        BottomSection(
//            seatCount = seatCount,
//            selectedSeats = selectedSeatNames.joinToString(","),
//            totalPrice = totalPrice,
//            onConfirmClick = {
//                if (seatCount > 0) {
//                    onConfirm(flight, selectedSeatNames.joinToString(","), totalPrice)
//                } else {
//                    Toast.makeText(context, "Please select your seat", Toast.LENGTH_SHORT).show()
//                }
//            },
//            modifier = Modifier.constrainAs(bottomSection) {
//                bottom.linkTo(parent.bottom)
//                start.linkTo(parent.start)
//                end.linkTo(parent.end)
//            }
//        )
//    }
//}
//
//fun generateSeatList(flight: FlightModel): List<Seat> {
//    val seatList = mutableListOf<Seat>()
//    // Giả định Business Class có tối đa 20 ghế (4 hàng, mỗi hàng 6 ghế + 1 khoảng trống)
//    val businessSeatCount = minOf(flight.NumberSeat, 20)
//    val rows = (businessSeatCount + 5) / 6 // 6 ghế mỗi hàng, trừ khoảng trống
//    val reservedSeats = flight.ReservedSeats?.split(",")?.toSet() ?: emptySet()
//
//    Log.d(
//        "SeatListScreen",
//        "numberSeat: ${flight.NumberSeat}, businessSeatCount: $businessSeatCount, rows: $rows"
//    )
//
//    if (businessSeatCount <= 0) {
//        Log.d("SeatListScreen", "No seats available due to numberSeat <= 0")
//        return emptyList()
//    }
//
//    val seatAlphabetMap = mapOf(
//        0 to "A",
//        1 to "B",
//        2 to "C",
//        4 to "D",
//        5 to "E",
//        6 to "F"
//    )
//
//    for (row in 1..rows) {
//        for (col in 0 until 7) {
//            if (col == 3) {
//                seatList.add(Seat(SeatStatus.EMPTY, "$row"))
//            } else {
//                val seatName = "${seatAlphabetMap[col]}$row"
//                val seatStatus = if (reservedSeats.contains(seatName)) {
//                    SeatStatus.UNAVAILABLE
//                } else {
//                    SeatStatus.AVAILABLE
//                }
//                seatList.add(Seat(seatStatus, seatName))
//            }
//        }
//    }
//
//    // Kiểm tra số ghế khả dụng
//    val availableSeats = seatList.count { it.status == SeatStatus.AVAILABLE }
//    Log.d("SeatListScreen", "Total seats: ${seatList.size}, Available seats: $availableSeats")
//
//    return seatList
//}

package com.example.ticketbookingapp.Activities.SeatSelect

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ticketbookingapp.Domain.FlightModel
import com.example.ticketbookingapp.R

enum class SeatStatus {
    AVAILABLE,
    SELECTED,
    UNAVAILABLE,
    EMPTY
}

data class Seat(
    var status: SeatStatus,
    var name: String
)

@Composable
fun SeatListScreen(
    flight: FlightModel,
    numPassenger: Int, // Thêm tham số numPassenger
    onBackClick: () -> Unit,
    onConfirm: (FlightModel, String, Double) -> Unit
) {
    val context = LocalContext.current
    val seatList = remember { mutableStateListOf<Seat>() }
    val selectedSeatNames = remember { mutableStateListOf<String>() }
    var seatCount by remember { mutableStateOf(0) }
    var totalPrice by remember { mutableStateOf(0.0) }

    LaunchedEffect(Unit) {
        seatList.clear()
        val seats = generateSeatList(flight)
        Log.d(
            "SeatListScreen",
            "Generated seats: ${seats.size}, ReservedSeats: ${flight.ReservedSeats}"
        )
        seatList.addAll(seats)
        seatCount = selectedSeatNames.size
        totalPrice = seatCount * flight.Price
    }

    fun updatePriceAndCount() {
        seatCount = selectedSeatNames.size
        totalPrice = seatCount * flight.Price
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.darkPurple2))
    ) {
        val (topSection, middSection, bottomSection) = createRefs()

        TopSection(
            modifier = Modifier
                .constrainAs(topSection) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            onBackClick = onBackClick
        )

        ConstraintLayout(
            modifier = Modifier
                .padding(top = 80.dp)
                .constrainAs(middSection) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            val (airplane, seatGrid) = createRefs()
            Image(
                painter = painterResource(R.drawable.airple_seat),
                contentDescription = "Airplane layout",
                modifier = Modifier.constrainAs(airplane) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            if (seatList.isEmpty()) {
                Text(
                    text = "No seats available for Business Class",
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 240.dp)
                        .constrainAs(seatGrid) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(7),
                    modifier = Modifier
                        .padding(top = 200.dp)
                        .padding(horizontal = 64.dp)
                        .constrainAs(seatGrid) {
                            top.linkTo(parent.top)
                            start.linkTo(airplane.start)
                            end.linkTo(airplane.end)
                        }
                ) {
                    items(seatList.size) { index ->
                        val seat = seatList[index]
                        SeatItem(
                            seat = seat,
                            onSeatClick = {
                                when (seat.status) {
                                    SeatStatus.AVAILABLE -> {
                                        if (selectedSeatNames.size < numPassenger) {
                                            seat.status = SeatStatus.SELECTED
                                            selectedSeatNames.add(seat.name)
                                            updatePriceAndCount()
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "You can only select $numPassenger seats",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }

                                    SeatStatus.SELECTED -> {
                                        seat.status = SeatStatus.AVAILABLE
                                        selectedSeatNames.remove(seat.name)
                                        updatePriceAndCount()
                                    }

                                    else -> {}
                                }
                            }
                        )
                    }
                }
            }
        }

        BottomSection(
            seatCount = seatCount,
            selectedSeats = selectedSeatNames.joinToString(","),
            totalPrice = totalPrice,
            onConfirmClick = {
                if (seatCount == numPassenger) {
                    onConfirm(flight, selectedSeatNames.joinToString(","), totalPrice)
                } else {
                    Toast.makeText(
                        context,
                        "Please select exactly $numPassenger seats",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier.constrainAs(bottomSection) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}

fun generateSeatList(flight: FlightModel): List<Seat> {
    val seatList = mutableListOf<Seat>()
    // Giả định Business Class có tối đa 20 ghế (4 hàng, mỗi hàng 6 ghế + 1 khoảng trống)
    val businessSeatCount = minOf(flight.NumberSeat, 20)
    val rows = (businessSeatCount + 5) / 6 // 6 ghế mỗi hàng, trừ khoảng trống
    val reservedSeats = flight.ReservedSeats?.split(",")?.toSet() ?: emptySet()

    Log.d(
        "SeatListScreen",
        "numberSeat: ${flight.NumberSeat}, businessSeatCount: $businessSeatCount, rows: $rows"
    )

    if (businessSeatCount <= 0) {
        Log.d("SeatListScreen", "No seats available due to numberSeat <= 0")
        return emptyList()
    }

    val seatAlphabetMap = mapOf(
        0 to "A",
        1 to "B",
        2 to "C",
        4 to "D",
        5 to "E",
        6 to "F"
    )

    for (row in 1..rows) {
        for (col in 0 until 7) {
            if (col == 3) {
                seatList.add(Seat(SeatStatus.EMPTY, "$row"))
            } else {
                val seatName = "${seatAlphabetMap[col]}$row"
                val seatStatus = if (reservedSeats.contains(seatName)) {
                    SeatStatus.UNAVAILABLE
                } else {
                    SeatStatus.AVAILABLE
                }
                seatList.add(Seat(seatStatus, seatName))
            }
        }
    }

    // Kiểm tra số ghế khả dụng
    val availableSeats = seatList.count { it.status == SeatStatus.AVAILABLE }
    Log.d("SeatListScreen", "Total seats: ${seatList.size}, Available seats: $availableSeats")

    return seatList
}