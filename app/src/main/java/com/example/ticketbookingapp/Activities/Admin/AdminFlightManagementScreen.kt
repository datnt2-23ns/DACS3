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
import com.example.ticketbookingapp.Domain.FlightModel
import com.example.ticketbookingapp.Domain.UserModel
import com.example.ticketbookingapp.R
import com.example.ticketbookingapp.ViewModel.AdminViewModel
import kotlinx.coroutines.launch

@Composable
fun AdminFlightManagementScreen(
    adminViewModel: AdminViewModel,
    user: UserModel,
    onBackToDashboard: () -> Unit
) {
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val flights by adminViewModel.flights.collectAsState()
    val context = LocalContext.current
    val TAG = "AdminFlightManagementScreen"

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
                text = "Quản Lý Chuyến Bay",
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

            flights.forEach { flight ->
                FlightItem(flight = flight, onDelete = {
                    coroutineScope.launch {
                        isLoading = true
                        try {
                            adminViewModel.deleteFlight(flight.FlightId) // Sử dụng FlightId
                            Toast.makeText(context, "Xóa chuyến bay thành công", Toast.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            Toast.makeText(context, "Lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
                        } finally {
                            isLoading = false
                        }
                    }
                })
            }

            Spacer(modifier = Modifier.height(32.dp))
            GradientButton(
                onClick = { /* TODO: Mở form thêm chuyến bay */ },
                text = "Thêm Chuyến Bay",
                padding = 0
            )
            Spacer(modifier = Modifier.height(16.dp))
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
fun FlightItem(flight: FlightModel, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(colorResource(R.color.darkPurple), shape = MaterialTheme.shapes.medium),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "${flight.AirlineName} (${flight.FlightId})", color = Color.White)
            Text(text = "${flight.From} -> ${flight.To}", color = Color.White)
            Text(text = "Ngày: ${flight.Date}", color = Color.White)
        }
        Button(onClick = onDelete) {
            Text("Xóa")
        }
    }
}