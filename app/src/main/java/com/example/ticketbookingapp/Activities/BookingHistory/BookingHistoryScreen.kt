//package com.example.ticketbookingapp.Activities.BookingHistory
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.shadow
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.constraintlayout.compose.ConstraintLayout
//import com.example.ticketbookingapp.Activities.Dashboard.MyBottomBar
//import com.example.ticketbookingapp.Activities.Dashboard.YellowTitle
//import com.example.ticketbookingapp.Domain.BookingModel
//import com.example.ticketbookingapp.Domain.UserModel
//import com.example.ticketbookingapp.R
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//import java.text.SimpleDateFormat
//import java.util.*
//
//@Composable
//fun BookingHistoryScreen(user: UserModel) {
//    val upcomingBookings = remember { mutableStateListOf<BookingModel>() }
//    val pastBookings = remember { mutableStateListOf<BookingModel>() }
//    val dateFormat = SimpleDateFormat("dd MMM, yyyy", Locale.US).apply {
//        isLenient = true
//    }
//    val currentDate = Calendar.getInstance().time
//
//    LaunchedEffect(Unit) {
//        val bookingsRef = FirebaseDatabase.getInstance().getReference("Bookings").child(user.username)
//        bookingsRef.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                upcomingBookings.clear()
//                pastBookings.clear()
//                for (bookingSnapshot in snapshot.children) {
//                    val booking = bookingSnapshot.getValue(BookingModel::class.java)
//                    if (booking != null) {
//                        try {
//                            val formattedDate = booking.date.replaceFirstChar { it.uppercaseChar() }
//                            val bookingDate = dateFormat.parse(formattedDate)
//                            if (bookingDate != null) {
//                                if (bookingDate.after(currentDate)) {
//                                    upcomingBookings.add(booking)
//                                } else {
//                                    pastBookings.add(booking)
//                                }
//                            }
//                        } catch (e: Exception) {
//                            println("Error parsing date: ${booking.date}, ${e.message}")
//                        }
//                    }
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                println("Firebase error: ${error.message}")
//            }
//        })
//    }
//
//    Scaffold(
//        bottomBar = { MyBottomBar(user = user, currentScreen = "BookingHistory") },
//        modifier = Modifier.fillMaxSize()
//    ) { paddingValues ->
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(colorResource(R.color.darkPurple2))
//                .padding(paddingValues)
//                .padding(horizontal = 32.dp),
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            item {
//                BookingHistoryTopBar(user = user)
//            }
//
//            item {
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(
//                            colorResource(R.color.darkPurple),
//                            shape = RoundedCornerShape(20.dp)
//                        )
//                        .padding(vertical = 16.dp, horizontal = 24.dp)
//                ) {
//                    YellowTitle("Upcoming Bookings")
//                    Spacer(modifier = Modifier.height(16.dp))
//                    if (upcomingBookings.isEmpty()) {
//                        Text(
//                            text = "No upcoming bookings",
//                            color = Color.Black,
//                            fontWeight = FontWeight.Normal,
//                            fontSize = 16.sp,
//                            modifier = Modifier.padding(top = 8.dp)
//                        )
//                    } else {
//                        upcomingBookings.forEach { booking ->
//                            BookingCard(booking)
//                            Divider(
//                                color = Color.Black.copy(alpha = 0.2f),
//                                thickness = 1.dp,
//                                modifier = Modifier.padding(vertical = 8.dp)
//                            )
//                        }
//                    }
//                }
//            }
//
//            item {
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(
//                            colorResource(R.color.darkPurple),
//                            shape = RoundedCornerShape(20.dp)
//                        )
//                        .padding(vertical = 16.dp, horizontal = 24.dp)
//                ) {
//                    YellowTitle("Past Bookings")
//                    Spacer(modifier = Modifier.height(16.dp))
//                    if (pastBookings.isEmpty()) {
//                        Text(
//                            text = "No past bookings",
//                            color = Color.Black,
//                            fontWeight = FontWeight.Normal,
//                            fontSize = 16.sp,
//                            modifier = Modifier.padding(top = 8.dp)
//                        )
//                    } else {
//                        pastBookings.forEach { booking ->
//                            BookingCard(booking)
//                            Divider(
//                                color = Color.Black.copy(alpha = 0.2f),
//                                thickness = 1.dp,
//                                modifier = Modifier.padding(vertical = 8.dp)
//                            )
//                        }
//                    }
//                }
//            }
//
//            item {
//                Spacer(modifier = Modifier.height(16.dp))
//            }
//        }
//    }
//}
//
//@Composable
//fun BookingHistoryTopBar(user: UserModel) {
//    ConstraintLayout(
//        modifier = Modifier
//            .padding(horizontal = 32.dp)
//            .fillMaxWidth()
//            .wrapContentHeight()
//    ) {
//        val (world, name, profile, notification, title) = createRefs()
//        Image(
//            painter = painterResource(R.drawable.world),
//            contentDescription = null,
//            modifier = Modifier
//                .clickable { }
//                .constrainAs(world) {
//                    top.linkTo(parent.top)
//                    bottom.linkTo(parent.bottom)
//                    start.linkTo(parent.start)
//                }
//        )
//
//        Image(
//            painter = painterResource(R.drawable.profile),
//            contentDescription = null,
//            modifier = Modifier
//                .constrainAs(profile) {
//                    top.linkTo(parent.top)
//                    bottom.linkTo(parent.bottom)
//                    start.linkTo(parent.start)
//                }
//        )
//
//        Image(
//            painter = painterResource(R.drawable.bell_icon),
//            contentDescription = null,
//            modifier = Modifier
//                .constrainAs(notification) {
//                    top.linkTo(parent.top)
//                    bottom.linkTo(parent.bottom)
//                    end.linkTo(parent.end)
//                }
//        )
//
//        Text(
//            text = user.fullName,
//            color = Color.White,
//            fontWeight = FontWeight.Normal,
//            fontSize = 14.sp,
//            modifier = Modifier.constrainAs(name) {
//                top.linkTo(parent.top)
//                start.linkTo(profile.end)
//                bottom.linkTo(parent.bottom)
//            }
//        )
//
//        Text(
//            text = "Booking History",
//            color = Color.White,
//            fontWeight = FontWeight.Bold,
//            fontSize = 25.sp,
//            modifier = Modifier
//                .constrainAs(title) {
//                    top.linkTo(profile.bottom)
//                    bottom.linkTo(parent.bottom)
//                }
//        )
//    }
//}
//
//@Composable
//fun BookingCard(booking: BookingModel) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .shadow(elevation = 8.dp, shape = RoundedCornerShape(12.dp)),
//        colors = CardDefaults.cardColors(
//            containerColor = colorResource(R.color.lightPurple)
//        ),
//        shape = RoundedCornerShape(12.dp)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(
//                    text = "${booking.from} to ${booking.to}",
//                    color = Color.Black,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 16.sp
//                )
//                Text(
//                    text = "$${booking.price}",
//                    color = colorResource(R.color.orange),
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 16.sp
//                )
//            }
//            Spacer(modifier = Modifier.height(8.dp))
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Row {
//                    Text(
//                        text = "Date: ",
//                        color = Color.Black,
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 14.sp
//                    )
//                    Text(
//                        text = "${booking.date}",
//                        color = Color.Black,
//                        fontWeight = FontWeight.Normal,
//                        fontSize = 14.sp
//                    )
//                }
//                Row {
//                    Text(
//                        text = "Class: ",
//                        color = Color.Black,
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 14.sp
//                    )
//                    Text(
//                        text = "${booking.typeClass}",
//                        color = Color.Black,
//                        fontWeight = FontWeight.Normal,
//                        fontSize = 14.sp
//                    )
//                }
//            }
//            Spacer(modifier = Modifier.height(4.dp))
//            Row {
//                Text(
//                    text = "Seats: ",
//                    color = Color.Black,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 14.sp
//                )
//                Text(
//                    text = "${booking.seats}",
//                    color = Color.Black,
//                    fontWeight = FontWeight.Normal,
//                    fontSize = 14.sp
//                )
//            }
//            Spacer(modifier = Modifier.height(4.dp))
//            Row {
//                Text(
//                    text = "Booking Time: ",
//                    color = Color.Black,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 14.sp
//                )
//                Text(
//                    text = booking.bookingDate,
//                    color = Color.Black,
//                    fontWeight = FontWeight.Normal,
//                    fontSize = 14.sp
//                )
//            }
//        }
//    }
//}

package com.example.ticketbookingapp.Activities.BookingHistory

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.example.ticketbookingapp.Activities.Dashboard.MyBottomBar
import com.example.ticketbookingapp.Activities.Dashboard.YellowTitle
import com.example.ticketbookingapp.Activities.TicketDetail.TicketDetailActivity
import com.example.ticketbookingapp.Domain.BookingModel
import com.example.ticketbookingapp.Domain.FlightModel
import com.example.ticketbookingapp.Domain.UserModel
import com.example.ticketbookingapp.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun BookingHistoryScreen(user: UserModel) {
    val upcomingBookings = remember { mutableStateListOf<BookingModel>() }
    val pastBookings = remember { mutableStateListOf<BookingModel>() }
    val dateFormat = SimpleDateFormat("dd MMM, yyyy", Locale.US).apply {
        isLenient = true
    }
    val currentDate = Calendar.getInstance().time

    LaunchedEffect(Unit) {
        val bookingsRef = FirebaseDatabase.getInstance().getReference("Bookings").child(user.username)
        bookingsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                upcomingBookings.clear()
                pastBookings.clear()
                for (bookingSnapshot in snapshot.children) {
                    val booking = bookingSnapshot.getValue(BookingModel::class.java)
                    if (booking != null) {
                        try {
                            val formattedDate = booking.date.replaceFirstChar { it.uppercaseChar() }
                            val bookingDate = dateFormat.parse(formattedDate)
                            if (bookingDate != null) {
                                if (bookingDate.after(currentDate)) {
                                    upcomingBookings.add(booking)
                                } else {
                                    pastBookings.add(booking)
                                }
                            }
                        } catch (e: Exception) {
                            println("Error parsing date: ${booking.date}, ${e.message}")
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println("Firebase error: ${error.message}")
            }
        })
    }

    Scaffold(
        bottomBar = { MyBottomBar(user = user, currentScreen = "BookingHistory") },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.darkPurple2))
                .padding(paddingValues)
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp) // Tăng khoảng cách giữa các section
        ) {
            item {
                BookingHistoryTopBar(user = user)
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp) // Giảm padding để sát lề
                ) {
                    YellowTitle("Upcoming Bookings")
                    Spacer(modifier = Modifier.height(16.dp))
                    if (upcomingBookings.isEmpty()) {
                        Text(
                            text = "No upcoming bookings",
                            color = Color.Black,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    } else {
                        upcomingBookings.forEach { booking ->
                            BookingItem(booking, user)
                            Divider(
                                color = Color.Black.copy(alpha = 0.2f),
                                thickness = 1.dp,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp) // Giảm padding để sát lề
                ) {
                    YellowTitle("Past Bookings")
                    Spacer(modifier = Modifier.height(16.dp))
                    if (pastBookings.isEmpty()) {
                        Text(
                            text = "No past bookings",
                            color = Color.Black,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    } else {
                        pastBookings.forEach { booking ->
                            BookingItem(booking, user)
                            Divider(
                                color = Color.Black.copy(alpha = 0.2f),
                                thickness = 1.dp,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun BookingHistoryTopBar(user: UserModel) {
    ConstraintLayout(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val (world, name, profile, notification, title) = createRefs()
        Image(
            painter = painterResource(R.drawable.world),
            contentDescription = null,
            modifier = Modifier
                .clickable { }
                .constrainAs(world) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )

        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(profile) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )

        Image(
            painter = painterResource(R.drawable.bell_icon),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(notification) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = user.fullName,
            color = Color.White,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            modifier = Modifier.constrainAs(name) {
                top.linkTo(parent.top)
                start.linkTo(profile.end)
                bottom.linkTo(parent.bottom)
            }
        )

        Text(
            text = "Booking History",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(profile.bottom)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}

@Composable
fun BookingItem(
    booking: BookingModel,
    user: UserModel
) {
    val context = LocalContext.current

    // Tạo FlightModel từ BookingModel để truyền sang TicketDetailActivity
    val flight = FlightModel(
        FlightId = booking.flightId,
        AirlineName = booking.airlineName,
        AirlineLogo = booking.airlineLogo,
        ArriveTime = booking.arriveTime,
        ClassSeat = booking.classSeat,
        TypeClass = booking.typeClass,
        Date = booking.date,
        From = booking.from,
        FromShort = booking.fromShort,
        Price = booking.price,
        To = booking.to,
        ToShort = booking.toShort,
        Time = booking.time,
        bookingTime = booking.bookingDate
    )

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth() // Vé trải rộng toàn chiều rộng
            .padding(vertical = 8.dp) // Giảm padding ngang để dài hơn
            .clickable {
                val intent = Intent(context, TicketDetailActivity::class.java).apply {
                    putExtra("flight", flight)
                    putExtra("user", user)
                    putExtra("selectedSeats", booking.seats)
                    putExtra("totalPrice", booking.price)
                    putExtra("bookingTime", booking.bookingDate)
                    putExtra("isHistoryView", true)
                }
                context.startActivity(intent)
            }
            .background(
                color = colorResource(R.color.lightPurple),
                shape = RoundedCornerShape(15.dp)
            )
    ) {
        val (
            logo, timeTxt, airplaneIcon, dashLine,
            priceTxt, seatIcon, classTxt,
            fromTxt, fromShortTxt, toTxt, toShortTxt
        ) = createRefs()

        if (booking.airlineLogo.isNotBlank()) {
            AsyncImage(
                model = booking.airlineLogo,
                contentDescription = "Airline logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(width = 120.dp, height = 40.dp)
                    .constrainAs(logo) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    },
                onState = { state ->
                    if (state is AsyncImagePainter.State.Error) {
                        println("Error loading logo: ${state.result.throwable.message}")
                    }
                }
            )
        } else {
            Box(
                modifier = Modifier
                    .size(width = 120.dp, height = 40.dp)
                    .constrainAs(logo) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
            )
        }

        Text(
            text = booking.arriveTime.takeIf { it.isNotBlank() } ?: "N/A",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = colorResource(R.color.darkPurple2),
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(timeTxt) {
                    start.linkTo(parent.start)
                    top.linkTo(logo.bottom)
                    end.linkTo(parent.end)
                }
        )

        Image(
            painter = painterResource(R.drawable.line_airple_blue),
            contentDescription = "Flight path icon",
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(airplaneIcon) {
                    start.linkTo(parent.start)
                    top.linkTo(timeTxt.bottom)
                    end.linkTo(parent.end)
                },
            contentScale = ContentScale.Fit
        )

        Image(
            painter = painterResource(R.drawable.dash_line),
            contentDescription = "Separator line",
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(dashLine) {
                    start.linkTo(parent.start)
                    top.linkTo(airplaneIcon.bottom)
                    end.linkTo(parent.end)
                },
            contentScale = ContentScale.FillWidth
        )

        Text(
            text = "$${String.format("%.2f", booking.price)}",
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp,
            color = colorResource(R.color.orange),
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(priceTxt) {
                    top.linkTo(dashLine.bottom)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end, margin = 16.dp)
                }
        )

        Image(
            painter = painterResource(R.drawable.seat_black_ic),
            contentDescription = "Seat icon",
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(seatIcon) {
                    start.linkTo(parent.start, margin = 16.dp)
                    top.linkTo(dashLine.bottom)
                    bottom.linkTo(parent.bottom)
                }
        )

        Text(
            text = booking.typeClass.takeIf { it.isNotBlank() } ?: "N/A",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = colorResource(R.color.darkPurple2),
            modifier = Modifier
                .constrainAs(classTxt) {
                    start.linkTo(seatIcon.end, margin = 4.dp)
                    top.linkTo(seatIcon.top)
                    bottom.linkTo(seatIcon.bottom)
                }
        )

        Text(
            text = booking.from.takeIf { it.isNotBlank() } ?: "N/A",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 16.dp)
                .constrainAs(fromTxt) {
                    top.linkTo(timeTxt.bottom)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = booking.fromShort.takeIf { it.isNotBlank() } ?: "N/A",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 16.dp)
                .constrainAs(fromShortTxt) {
                    top.linkTo(fromTxt.bottom)
                    start.linkTo(fromTxt.start)
                    end.linkTo(fromTxt.end)
                }
        )

        Text(
            text = booking.to.takeIf { it.isNotBlank() } ?: "N/A",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(end = 16.dp)
                .constrainAs(toTxt) {
                    top.linkTo(timeTxt.bottom)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = booking.toShort.takeIf { it.isNotBlank() } ?: "N/A",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier
                .padding(end = 16.dp)
                .constrainAs(toShortTxt) {
                    top.linkTo(toTxt.bottom)
                    start.linkTo(toTxt.start)
                    end.linkTo(toTxt.end)
                }
        )
    }
}