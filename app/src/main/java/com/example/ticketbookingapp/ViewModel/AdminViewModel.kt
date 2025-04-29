package com.example.ticketbookingapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketbookingapp.Domain.BookingModel
import com.example.ticketbookingapp.Domain.FlightModel
import com.example.ticketbookingapp.Repository.AdminRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class BookingWithMetadata(
    val booking: BookingModel, // Sửa: thêm kiểu BookingModel rõ ràng
    val bookingId: String,
    val userId: String
)

class AdminViewModel : ViewModel() {
    private val adminRepository = AdminRepository()

    private val _flights = MutableStateFlow<List<FlightModel>>(emptyList())
    val flights: StateFlow<List<FlightModel>> = _flights

    private val _bookings = MutableStateFlow<List<BookingWithMetadata>>(emptyList())
    val bookings: StateFlow<List<BookingWithMetadata>> = _bookings

    init {
        loadFlights()
        loadBookings()
    }

    private fun loadFlights() {
        viewModelScope.launch {
            try {
                _flights.value = adminRepository.getAllFlights()
            } catch (e: Exception) {
                // Xử lý lỗi
            }
        }
    }

    private fun loadBookings() {
        viewModelScope.launch {
            try {
                _bookings.value = adminRepository.getAllBookings()
            } catch (e: Exception) {
                // Xử lý lỗi
            }
        }
    }

    fun deleteFlight(flightId: String) {
        viewModelScope.launch {
            try {
                adminRepository.deleteFlight(flightId)
                _flights.value = _flights.value.filter { it.FlightId != flightId }
            } catch (e: Exception) {
                // Xử lý lỗi
            }
        }
    }

    fun cancelBooking(userId: String, bookingId: String) {
        viewModelScope.launch {
            try {
                adminRepository.cancelBooking(userId, bookingId)
                _bookings.value = _bookings.value.filter { it.bookingId != bookingId || it.userId != userId }
            } catch (e: Exception) {
                // Xử lý lỗi
            }
        }
    }
}