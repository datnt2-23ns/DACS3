package com.example.ticketbookingapp.Activities.SeatSelect

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.ticketbookingapp.Activities.Splash.StatusTopBarColor
import com.example.ticketbookingapp.Activities.TicketDetail.TicketDetailActivity
import com.example.ticketbookingapp.Domain.FlightModel

class SeatSelectActivity : AppCompatActivity() {
    private lateinit var flight: FlightModel
    private var numPassenger: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        flight = intent.getSerializableExtra("flight") as FlightModel
        numPassenger = intent.getStringExtra("numPassenger")?.toIntOrNull() ?: 0

        setContent {
            StatusTopBarColor()

            SeatListScreen(
                flight = flight,
                numPassenger = numPassenger,
                onBackClick = {
                    finish()
                },
                onConfirm = { updatedFlight, selectedSeats, totalPrice ->
                    val intent = Intent(this, TicketDetailActivity::class.java).apply {
                        putExtra("flight", updatedFlight)
                        putExtra("selectedSeats", selectedSeats)
                        putExtra("totalPrice", totalPrice)
                    }
                    startActivity(intent)
                }
            )
        }
    }
}