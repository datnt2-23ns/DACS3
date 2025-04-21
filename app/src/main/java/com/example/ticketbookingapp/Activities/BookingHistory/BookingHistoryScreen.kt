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
//                .padding(horizontal = 32.dp), // Match Dashboard padding
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
//                    if (upcomingBookings.isEmpty()) {
//                        Text(
//                            text = "No upcoming bookings",
//                            color = Color.White,
//                            fontSize = 16.sp,
//                            modifier = Modifier.padding(top = 8.dp)
//                        )
//                    } else {
//                        upcomingBookings.forEach { booking ->
//                            BookingCard(booking)
//                            Divider(
//                                color = Color.White.copy(alpha = 0.2f),
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
//                    if (pastBookings.isEmpty()) {
//                        Text(
//                            text = "No past bookings",
//                            color = Color.White,
//                            fontSize = 16.sp,
//                            modifier = Modifier.padding(top = 8.dp)
//                        )
//                    } else {
//                        pastBookings.forEach { booking ->
//                            BookingCard(booking)
//                            Divider(
//                                color = Color.White.copy(alpha = 0.2f),
//                                thickness = 1.dp,
//                                modifier = Modifier.padding(vertical = 8.dp)
//                            )
//                        }
//                    }
//                }
//            }
//
//            // Spacer cuối để đảm bảo cuộn
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
//            fontWeight = FontWeight.Bold,
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
//                    color = Color.White,
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Bold
//                )
//                Text(
//                    text = "$${booking.price}",
//                    color = colorResource(R.color.orange),
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Bold
//                )
//            }
//            Spacer(modifier = Modifier.height(8.dp))
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(
//                    text = "Date: ${booking.date}",
//                    color = Color.White.copy(alpha = 0.8f),
//                    fontSize = 14.sp
//                )
//                Text(
//                    text = "Class: ${booking.typeClass}",
//                    color = Color.White.copy(alpha = 0.8f),
//                    fontSize = 14.sp
//                )
//            }
//            Spacer(modifier = Modifier.height(4.dp))
//            Text(
//                text = "Seats: ${booking.seats}",
//                color = Color.White.copy(alpha = 0.8f),
//                fontSize = 14.sp
//            )
//        }
//    }
//}
//
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
//                            fontWeight = FontWeight.Bold,
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
//                            fontWeight = FontWeight.Bold,
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
//            // Spacer cuối để đảm bảo cuộn
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
//            fontWeight = FontWeight.Bold,
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
//                Text(
//                    text = "Date: ${booking.date}",
//                    color = Color.Black,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 14.sp
//                )
//                Text(
//                    text = "Class: ${booking.typeClass}",
//                    color = Color.Black,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 14.sp
//                )
//            }
//            Spacer(modifier = Modifier.height(4.dp))
//            Text(
//                text = "Seats: ${booking.seats}",
//                color = Color.Black,
//                fontWeight = FontWeight.Bold,
//                fontSize = 14.sp
//            )
//        }
//    }
//}

package com.example.ticketbookingapp.Activities.BookingHistory

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ticketbookingapp.Activities.Dashboard.MyBottomBar
import com.example.ticketbookingapp.Activities.Dashboard.YellowTitle
import com.example.ticketbookingapp.Domain.BookingModel
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                BookingHistoryTopBar(user = user)
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            colorResource(R.color.darkPurple),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(vertical = 16.dp, horizontal = 24.dp)
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
                            BookingCard(booking)
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
                        .background(
                            colorResource(R.color.darkPurple),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(vertical = 16.dp, horizontal = 24.dp)
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
                            BookingCard(booking)
                            Divider(
                                color = Color.Black.copy(alpha = 0.2f),
                                thickness = 1.dp,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }
                }
            }

            // Spacer cuối để đảm bảo cuộn
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
fun BookingCard(booking: BookingModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.lightPurple)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${booking.from} to ${booking.to}",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold, // In đậm
                    fontSize = 16.sp
                )
                Text(
                    text = "$${booking.price}",
                    color = colorResource(R.color.orange),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Text(
                        text = "Date: ",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "${booking.date}",
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )
                }
                Row {
                    Text(
                        text = "Class: ",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "${booking.typeClass}",
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Text(
                    text = "Seats: ",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Text(
                    text = "${booking.seats}",
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            }
        }
    }
}