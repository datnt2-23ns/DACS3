package com.example.ticketbookingapp.Activities.TicketDetail

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.ticketbookingapp.Activities.Splash.StatusTopBarColor
import com.example.ticketbookingapp.Domain.FlightModel

class TicketDetailActivity : AppCompatActivity() {
    private lateinit var flight: FlightModel
    private var selectedSeats: String = ""
    private var totalPrice: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Lấy dữ liệu từ Intent
        flight = intent.getSerializableExtra("flight") as FlightModel
        selectedSeats = intent.getStringExtra("selectedSeats") ?: ""
        totalPrice = intent.getDoubleExtra("totalPrice", 0.0)

        setContent {
            StatusTopBarColor()

            TicketDetailScreen(
                flight = flight,
                selectedSeats = selectedSeats, // Truyền selectedSeats
                totalPrice = totalPrice,      // Truyền totalPrice
                onBackClick = {
                    finish()
                },
                onDownloadTicketClick = {
                    // Xử lý tải vé (nếu cần)
                }
            )
        }
    }
}