package com.example.ticketbookingapp.Activities.Admin

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ticketbookingapp.Activities.Splash.GradientButton
import com.example.ticketbookingapp.Domain.UserModel
import com.example.ticketbookingapp.R
import com.example.ticketbookingapp.ViewModel.AdminViewModel
import com.example.ticketbookingapp.ViewModel.BookingWithMetadata
import kotlinx.coroutines.launch

@Composable
fun AdminBookingManagementScreen(
    adminViewModel: AdminViewModel,
    user: UserModel,
    onBackToDashboard: () -> Unit
) {
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val bookings by adminViewModel.bookings.collectAsState()
    val context = LocalContext.current
    val TAG = "AdminBookingManagementScreen"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.darkPurple2))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Quản Lý Đặt Vé",
                fontSize = 32.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Chào, ${user.fullName}",
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(32.dp))

            bookings.forEach { bookingWithMetadata ->
                BookingItem(
                    bookingWithMetadata = bookingWithMetadata,
                    onCancel = {
                        coroutineScope.launch {
                            isLoading = true
                            try {
                                adminViewModel.cancelBooking(
                                    bookingWithMetadata.userId,
                                    bookingWithMetadata.bookingId
                                )
                                Toast.makeText(context, "Hủy đặt vé thành công", Toast.LENGTH_SHORT).show()
                            } catch (e: Exception) {
                                Toast.makeText(context, "Lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
                            } finally {
                                isLoading = false
                            }
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
            GradientButton(
                onClick = { onBackToDashboard() },
                text = "Quay Lại Dashboard",
                padding = 0
            )
        }

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun BookingItem(bookingWithMetadata: BookingWithMetadata, onCancel: () -> Unit) {
    val booking = bookingWithMetadata.booking
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(colorResource(R.color.darkPurple), shape = MaterialTheme.shapes.medium),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "User: ${bookingWithMetadata.userId}", color = Color.White)
            Text(text = "Chuyến: ${booking.from} -> ${booking.to}", color = Color.White) // Sửa: From, To
            Text(text = "Ngày: ${booking.date}", color = Color.White) // Sửa: Date
            Text(text = "Ghế: ${booking.seats}", color = Color.White) // Sửa: Seats
        }
        Button(onClick = onCancel) {
            Text("Hủy")
        }
    }
}