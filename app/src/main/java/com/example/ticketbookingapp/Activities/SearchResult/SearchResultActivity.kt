//package com.example.ticketbookingapp.Activities.SearchResult
//
//import android.os.Bundle
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import com.example.ticketbookingapp.Activities.Splash.StatusTopBarColor
//import com.example.ticketbookingapp.ViewModel.MainViewModel
//
//class SearchResultActivity : AppCompatActivity() {
//    private lateinit var viewModel: MainViewModel
//    private var from: String = ""
//    private var to: String = ""
//    private var departureDate: String = ""
//    private var returnDate: String = ""
//    private var typeClass: String = ""
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//
//        // Khởi tạo ViewModel
//        viewModel = MainViewModel()
//
//        // Lấy dữ liệu từ Intent
//        from = intent.getStringExtra("from") ?: ""
//        to = intent.getStringExtra("to") ?: ""
//        departureDate = intent.getStringExtra("departureDate") ?: ""
//        returnDate = intent.getStringExtra("returnDate") ?: ""
//        typeClass = intent.getStringExtra("typeClass") ?: ""
//
//        // Log dữ liệu Intent
//        println("Intent data: from=$from, to=$to, departureDate=$departureDate, returnDate=$returnDate, typeClass=$typeClass")
//
//        setContent {
//            StatusTopBarColor()
//
//            ItemListScreen(
//                from = from,
//                to = to,
//                departureDate = departureDate,
//                returnDate = returnDate,
//                typeClass = typeClass,
//                viewModel = viewModel,
//                onBackClick = {
//                    finish()
//                }
//            )
//        }
//    }
//}

package com.example.ticketbookingapp.Activities.SearchResult

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.ticketbookingapp.Activities.Splash.StatusTopBarColor
import com.example.ticketbookingapp.ViewModel.MainViewModel

class SearchResultActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private var from: String = ""
    private var to: String = ""
    private var departureDate: String = ""
    private var returnDate: String = ""
    private var typeClass: String = ""
    private var numPassenger: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Khởi tạo ViewModel
        viewModel = MainViewModel()

        // Lấy dữ liệu từ Intent
        from = intent.getStringExtra("from") ?: ""
        to = intent.getStringExtra("to") ?: ""
        departureDate = intent.getStringExtra("departureDate") ?: ""
        returnDate = intent.getStringExtra("returnDate") ?: ""
        typeClass = intent.getStringExtra("typeClass") ?: ""
        numPassenger = intent.getStringExtra("numPassenger")?.toIntOrNull() ?: 0

        // Log dữ liệu Intent
        println("Intent data: from=$from, to=$to, departureDate=$departureDate, returnDate=$returnDate, typeClass=$typeClass, numPassenger=$numPassenger")

        setContent {
            StatusTopBarColor()

            ItemListScreen(
                from = from,
                to = to,
                departureDate = departureDate,
                returnDate = returnDate,
                typeClass = typeClass,
                numPassenger = numPassenger, // Truyền numPassenger vào ItemListScreen
                viewModel = viewModel,
                onBackClick = {
                    finish()
                }
            )
        }
    }
}