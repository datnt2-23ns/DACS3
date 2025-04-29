package com.example.ticketbookingapp.Repository

import com.example.ticketbookingapp.Domain.BookingModel
import com.example.ticketbookingapp.Domain.FlightModel
import com.example.ticketbookingapp.ViewModel.BookingWithMetadata
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class AdminRepository {
    private val database = FirebaseDatabase.getInstance().reference

    suspend fun getAllFlights(): List<FlightModel> {
        val snapshot = database.child("Flights").get().await()
        return snapshot.children.mapNotNull { it.getValue(FlightModel::class.java) }
    }

    suspend fun deleteFlight(flightId: String) {
        database.child("Flights").orderByChild("flightId").equalTo(flightId).get().await()
            .children.forEach { it.ref.removeValue().await() }
    }

    suspend fun getAllBookings(): List<BookingWithMetadata> {
        val snapshot = database.child("Bookings").get().await()
        val bookings = mutableListOf<BookingWithMetadata>()
        snapshot.children.forEach { userSnapshot ->
            val userId = userSnapshot.key ?: return@forEach
            userSnapshot.children.forEach { bookingSnapshot ->
                val booking = bookingSnapshot.getValue(BookingModel::class.java)
                val bookingId = bookingSnapshot.key ?: return@forEach
                if (booking != null) {
                    bookings.add(
                        BookingWithMetadata(
                            booking = booking, // Đảm bảo truyền booking không null
                            bookingId = bookingId,
                            userId = userId
                        )
                    )
                }
            }
        }
        return bookings
    }

    suspend fun cancelBooking(userId: String, bookingId: String) {
        database.child("Bookings").child(userId).child(bookingId).removeValue().await()
    }
}