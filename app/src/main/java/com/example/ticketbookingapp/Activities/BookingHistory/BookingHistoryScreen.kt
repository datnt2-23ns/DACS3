//package com.example.ticketbookingapp.Activities.BookingHistory
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.ticketbookingapp.Activities.Dashboard.MyBottomBar
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
//        // Đảm bảo xử lý tháng viết thường (jun, apr,...)
//        isLenient = true // Cho phép linh hoạt trong việc phân tích
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
//                            // Chuyển đổi định dạng ngày trước khi parse
//                            val formattedDate = booking.date.replaceFirstChar { it.uppercaseChar() } // Viết hoa chữ cái đầu của tháng (jun -> Jun)
//                            val bookingDate = dateFormat.parse(formattedDate)
//                            if (bookingDate != null) {
//                                if (bookingDate.after(currentDate)) {
//                                    upcomingBookings.add(booking)
//                                } else {
//                                    pastBookings.add(booking)
//                                }
//                            }
//                        } catch (e: Exception) {
//                            // Xử lý lỗi parse ngày (nếu cần)
//                            println("Error parsing date: ${booking.date}, ${e.message}")
//                        }
//                    }
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Xử lý lỗi nếu cần
//            }
//        })
//    }
//
//    Scaffold(
//        bottomBar = { MyBottomBar(user = user, currentScreen = "BookingHistory") }, // Truyền thông tin màn hình hiện tại
//        modifier = Modifier.fillMaxSize()
//    ) { paddingValues ->
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(colorResource(R.color.darkPurple2))
//                .padding(paddingValues)
//                .padding(horizontal = 16.dp)
//        ) {
//            item {
//                Text(
//                    text = "Upcoming Bookings",
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.SemiBold,
//                    color = colorResource(R.color.orange),
//                    modifier = Modifier.padding(bottom = 8.dp, top = 16.dp)
//                )
//            }
//            if (upcomingBookings.isEmpty()) {
//                item {
//                    Text(
//                        text = "No upcoming bookings",
//                        color = Color.White,
//                        fontSize = 16.sp,
//                        modifier = Modifier.padding(bottom = 16.dp)
//                    )
//                }
//            } else {
//                items(upcomingBookings) { booking ->
//                    BookingCard(booking)
//                }
//            }
//
//            item {
//                Text(
//                    text = "Past Bookings",
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.SemiBold,
//                    color = colorResource(R.color.orange),
//                    modifier = Modifier.padding(vertical = 8.dp)
//                )
//            }
//            if (pastBookings.isEmpty()) {
//                item {
//                    Text(
//                        text = "No past bookings",
//                        color = Color.White,
//                        fontSize = 16.sp,
//                        modifier = Modifier.padding(bottom = 16.dp)
//                    )
//                }
//            } else {
//                items(pastBookings) { booking ->
//                    BookingCard(booking)
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun BookingCard(booking: BookingModel) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 4.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = colorResource(R.color.darkPurple)
//        )
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            Text(
//                text = "${booking.from} to ${booking.to}",
//                color = Color.White,
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Bold
//            )
//            Spacer(modifier = Modifier.height(4.dp))
//            Text(
//                text = "Date: ${booking.date}",
//                color = Color.White,
//                fontSize = 14.sp
//            )
//            Text(
//                text = "Class: ${booking.typeClass}",
//                color = Color.White,
//                fontSize = 14.sp
//            )
//            Text(
//                text = "Seats: ${booking.seats}",
//                color = Color.White,
//                fontSize = 14.sp
//            )
//            Text(
//                text = "Price: $${booking.price}",
//                color = Color.White,
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
        ) {
            item {
                BookingHistoryTopBar(user = user)
            }

            item {
                Text(
                    text = "Upcoming Bookings",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(R.color.orange),
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                )
            }
            if (upcomingBookings.isEmpty()) {
                item {
                    Text(
                        text = "No upcoming bookings",
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                    )
                }
            } else {
                items(upcomingBookings) { booking ->
                    BookingCard(booking)
                    Divider(
                        color = Color.White.copy(alpha = 0.2f),
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }

            item {
                Text(
                    text = "Past Bookings",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(R.color.orange),
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                )
            }
            if (pastBookings.isEmpty()) {
                item {
                    Text(
                        text = "No past bookings",
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                    )
                }
            } else {
                items(pastBookings) { booking ->
                    BookingCard(booking)
                    Divider(
                        color = Color.White.copy(alpha = 0.2f),
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
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
            .padding(top = 16.dp)
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
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.constrainAs(name) {
                top.linkTo(parent.top)
                start.linkTo(profile.end, margin = 16.dp)
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
                    top.linkTo(profile.bottom, margin = 8.dp)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )
    }
}

@Composable
fun BookingCard(booking: BookingModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.darkPurple)
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
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$${booking.price}",
                    color = colorResource(R.color.orange),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Date: ${booking.date}",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 14.sp
                )
                Text(
                    text = "Class: ${booking.typeClass}",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Seats: ${booking.seats}",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp
            )
        }
    }
}