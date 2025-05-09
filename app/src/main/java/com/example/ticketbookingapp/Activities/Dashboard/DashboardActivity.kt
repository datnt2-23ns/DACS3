//package com.example.ticketbookingapp.Activities.Dashboard
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import android.widget.Toast
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalLifecycleOwner
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.ticketbookingapp.Activities.SearchResult.SearchResultActivity
//import com.example.ticketbookingapp.Activities.Splash.GradientButton
//import com.example.ticketbookingapp.Activities.Splash.StatusTopBarColor
//import com.example.ticketbookingapp.Domain.LocationModel
//import com.example.ticketbookingapp.Domain.UserModel
//import com.example.ticketbookingapp.R
//import com.example.ticketbookingapp.ViewModel.MainViewModel
//
//class DashboardActivity : AppCompatActivity() {
//    private val TAG = "DashboardActivity"
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//
//        // Lấy UserModel từ Intent
//        val user = intent.getSerializableExtra("user") as? UserModel
//        if (user == null) {
//            Log.e(TAG, "User data not found in Intent")
//            Toast.makeText(this, "Error: User data not found", Toast.LENGTH_SHORT).show()
//            finish()
//            return
//        }
//
//        Log.d(TAG, "Received user: ${user.username}")
//
//        setContent {
//            StatusTopBarColor()
//            MainScreen(user = user)
//        }
//    }
//
//    companion object {
//        fun newIntent(context: Context): Intent {
//            return Intent(context, DashboardActivity::class.java)
//        }
//    }
//}
//
//@Composable
//fun MainScreen(user: UserModel) {
//    val locations = remember { mutableStateListOf<LocationModel>() }
//    val viewModel = MainViewModel()
//    var showLocationLoading by remember { mutableStateOf(true) }
//    var from by remember { mutableStateOf("") }
//    var to by remember { mutableStateOf("") }
//    var typeClass by remember { mutableStateOf("") }
//    var adultPassenger by remember { mutableStateOf("0") }
//    var childPassenger by remember { mutableStateOf("0") }
//    var departureDate by remember { mutableStateOf("") }
//    var errorMessage by remember { mutableStateOf<String?>(null) }
//    val context = LocalContext.current
//    val lifecycleOwner = LocalLifecycleOwner.current
//    val TAG = "MainScreen"
//
//    StatusTopBarColor()
//
//    LaunchedEffect(Unit) {
//        try {
//            viewModel.loadLocations().observe(lifecycleOwner) { result ->
//                Log.d(TAG, "Received locations: ${result?.size ?: 0}")
//                if (result != null) {
//                    locations.clear()
//                    locations.addAll(result)
//                    showLocationLoading = false
//                } else {
//                    errorMessage = "Failed to load locations"
//                    showLocationLoading = false
//                }
//            }
//        } catch (e: Exception) {
//            Log.e(TAG, "Error loading locations: ${e.message}")
//            errorMessage = "Error loading locations: ${e.message}"
//            showLocationLoading = false
//        }
//    }
//
//    Scaffold(
//        bottomBar = { MyBottomBar(user = user, currentScreen = "Dashboard") },
//    ) { paddingValues ->
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(color = colorResource(R.color.darkPurple2))
//                .padding(paddingValues = paddingValues)
//        ) {
//            item {
//                TopBar(user = user)
//            }
//            item {
//                Column(
//                    modifier = Modifier
//                        .padding(32.dp)
//                        .background(
//                            colorResource(R.color.darkPurple),
//                            shape = RoundedCornerShape(20.dp)
//                        )
//                        .fillMaxWidth()
//                        .padding(vertical = 16.dp, horizontal = 24.dp)
//                ) {
//                    // From selection
//                    YellowTitle("From")
//                    val locationNames = locations.map { it.Name }
//                    DropDownList(
//                        items = locationNames,
//                        loadingIcon = painterResource(R.drawable.from_ic),
//                        hint = "Select origin",
//                        showLocationLoading = showLocationLoading,
//                        onItemSelected = { selectedItem ->
//                            from = selectedItem
//                        }
//                    )
//
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    // To selection
//                    YellowTitle("To")
//                    DropDownList(
//                        items = locationNames,
//                        loadingIcon = painterResource(R.drawable.from_ic),
//                        hint = "Select destination",
//                        showLocationLoading = showLocationLoading,
//                        onItemSelected = { selectedItem ->
//                            to = selectedItem
//                        }
//                    )
//
//                    // Passenger counter
//                    Spacer(modifier = Modifier.height(16.dp))
//                    YellowTitle("Passengers")
//                    Row(modifier = Modifier.fillMaxWidth()) {
//                        PassengerCounter(
//                            title = "Adult",
//                            modifier = Modifier.weight(1f),
//                            onItemSelected = { value ->
//                                val newValue = value.toIntOrNull() ?: 0
//                                adultPassenger = if (newValue >= 0) newValue.toString() else "0"
//                            }
//                        )
//                        Spacer(modifier = Modifier.width(16.dp))
//                        PassengerCounter(
//                            title = "Child",
//                            modifier = Modifier.weight(1f),
//                            onItemSelected = { value ->
//                                val newValue = value.toIntOrNull() ?: 0
//                                childPassenger = if (newValue >= 0) newValue.toString() else "0"
//                            }
//                        )
//                    }
//
//                    // Departure date và Class trên cùng một hàng
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Row(modifier = Modifier.fillMaxWidth()) {
//                        Column(modifier = Modifier.weight(1f)) {
//                            YellowTitle("Departure date")
//                            DatePickerScreen(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .height(60.dp)
//                                    .background(
//                                        color = colorResource(R.color.lightPurple),
//                                        shape = RoundedCornerShape(8.dp)
//                                    ),
//                                onDepartureSelected = { date ->
//                                    departureDate = date
//                                },
//                                onReturnSelected = null
//                            )
//                        }
//                        Spacer(modifier = Modifier.width(16.dp))
//                        Column(modifier = Modifier.weight(1f)) {
//                            YellowTitle("Class")
//                            val classItems = listOf("Business", "First", "Economy")
//                            DropDownList(
//                                items = classItems,
//                                loadingIcon = painterResource(R.drawable.seat_black_ic),
//                                hint = "Select class",
//                                showLocationLoading = showLocationLoading,
//                                onItemSelected = { selectedItem ->
//                                    typeClass = selectedItem
//                                },
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .height(60.dp)
//                                    .background(
//                                        color = colorResource(R.color.lightPurple),
//                                        shape = RoundedCornerShape(8.dp)
//                                    )
//                            )
//                        }
//                    }
//
//                    // Search button
//                    Spacer(modifier = Modifier.height(16.dp))
//                    GradientButton(
//                        onClick = {
//                            try {
//                                if (from.isEmpty() || to.isEmpty() || departureDate.isEmpty() || typeClass.isEmpty()) {
//                                    errorMessage = "Please fill all required fields"
//                                    return@GradientButton
//                                }
//                                val totalPassengers = (adultPassenger.toIntOrNull() ?: 0) +
//                                        (childPassenger.toIntOrNull() ?: 0)
//                                if (totalPassengers <= 0) {
//                                    errorMessage = "At least one passenger is required"
//                                    return@GradientButton
//                                }
//                                val intent = Intent(context, SearchResultActivity::class.java).apply {
//                                    putExtra("from", from)
//                                    putExtra("to", to)
//                                    putExtra("departureDate", departureDate)
//                                    putExtra("typeClass", typeClass)
//                                    putExtra("numPassenger", totalPassengers.toString())
//                                }
//                                context.startActivity(intent)
//                            } catch (e: Exception) {
//                                Log.e(TAG, "Error starting SearchResultActivity: ${e.message}")
//                                errorMessage = "Error: Unable to search flights"
//                            }
//                        },
//                        text = "Search"
//                    )
//
//                    // Error message
//                    errorMessage?.let { message ->
//                        Spacer(modifier = Modifier.height(16.dp))
//                        Text(
//                            text = message,
//                            color = Color.Red,
//                            fontSize = 14.sp,
//                            modifier = Modifier.padding(horizontal = 16.dp)
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun YellowTitle(text: String, modifier: Modifier = Modifier) {
//    Text(
//        text = text,
//        fontWeight = FontWeight.SemiBold,
//        color = colorResource(R.color.orange),
//        fontSize = 16.sp,
//        modifier = modifier
//    )
//}

package com.example.ticketbookingapp.Activities.Dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ticketbookingapp.Activities.SearchResult.SearchResultActivity
import com.example.ticketbookingapp.Activities.Splash.GradientButton
import com.example.ticketbookingapp.Activities.Splash.StatusTopBarColor
import com.example.ticketbookingapp.Domain.LocationModel
import com.example.ticketbookingapp.Domain.UserModel
import com.example.ticketbookingapp.R
import com.example.ticketbookingapp.ViewModel.MainViewModel

class DashboardActivity : AppCompatActivity() {
    private val TAG = "DashboardActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Lấy UserModel từ Intent
        val user = intent.getSerializableExtra("user") as? UserModel
        if (user == null) {
            Log.e(TAG, "User data not found in Intent")
            Toast.makeText(this, "Error: User data not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        Log.d(TAG, "Received user: ${user.username}")

        setContent {
            StatusTopBarColor()
            MainScreen(user = user)
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, DashboardActivity::class.java)
        }
    }
}

@Composable
fun MainScreen(user: UserModel) {
    val locations = remember { mutableStateListOf<LocationModel>() }
    val viewModel = MainViewModel()
    var showLocationLoading by remember { mutableStateOf(true) }
    var from by remember { mutableStateOf("") }
    var to by remember { mutableStateOf("") }
    var typeClass by remember { mutableStateOf("") }
    var adultPassenger by remember { mutableStateOf("0") }
    var childPassenger by remember { mutableStateOf("0") }
    var departureDate by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val TAG = "MainScreen"

    StatusTopBarColor()

    LaunchedEffect(Unit) {
        try {
            viewModel.loadLocations().observe(lifecycleOwner) { result ->
                Log.d(TAG, "Received locations: ${result?.size ?: 0}")
                if (result != null) {
                    locations.clear()
                    locations.addAll(result)
                    showLocationLoading = false
                } else {
                    errorMessage = "Failed to load locations"
                    showLocationLoading = false
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error loading locations: ${e.message}")
            errorMessage = "Error loading locations: ${e.message}"
            showLocationLoading = false
        }
    }

    Scaffold(
        bottomBar = { MyBottomBar(user = user, currentScreen = "Dashboard") },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.darkPurple2))
                .padding(paddingValues = paddingValues)
        ) {
            item {
                TopBar(user = user)
            }
            item {
                Column(
                    modifier = Modifier
                        .padding(32.dp)
                        .background(
                            colorResource(R.color.darkPurple),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 24.dp)
                ) {
                    // From selection
                    YellowTitle("From")
                    val locationNames = locations.map { it.Name }
                    DropDownList(
                        items = locationNames,
                        loadingIcon = painterResource(R.drawable.from_ic),
                        hint = "Select origin",
                        showLocationLoading = showLocationLoading,
                        onItemSelected = { selectedItem ->
                            from = selectedItem
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // To selection
                    YellowTitle("To")
                    DropDownList(
                        items = locationNames,
                        loadingIcon = painterResource(R.drawable.to_ic),
                        hint = "Select destination",
                        showLocationLoading = showLocationLoading,
                        onItemSelected = { selectedItem ->
                            to = selectedItem
                        }
                    )

                    // Passenger counter
                    Spacer(modifier = Modifier.height(16.dp))
                    YellowTitle("Passengers")
                    Row(modifier = Modifier.fillMaxWidth()) {
                        PassengerCounter(
                            title = "Adult",
                            modifier = Modifier.weight(1f),
                            onItemSelected = { value ->
                                val newValue = value.toIntOrNull() ?: 0
                                adultPassenger = if (newValue >= 0) newValue.toString() else "0"
                            }
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        PassengerCounter(
                            title = "Child",
                            modifier = Modifier.weight(1f),
                            onItemSelected = { value ->
                                val newValue = value.toIntOrNull() ?: 0
                                childPassenger = if (newValue >= 0) newValue.toString() else "0"
                            }
                        )
                    }

                    // Departure date và Class trên cùng một hàng
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            YellowTitle("Departure date")
                            Spacer(modifier = Modifier.height(10.dp)) //
                            DatePickerScreen(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .background(
                                        color = colorResource(R.color.lightPurple),
                                        shape = RoundedCornerShape(8.dp)
                                    ),
                                onDepartureSelected = { date ->
                                    departureDate = date
                                },
                                onReturnSelected = null
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            YellowTitle("Class")
                            Spacer(modifier = Modifier.height(10.dp)) //
                            val classItems = listOf("Business", "First", "Economy")
                            DropDownList(
                                items = classItems,
                                loadingIcon = painterResource(R.drawable.seat_black_ic),
                                hint = "Select class",
                                showLocationLoading = showLocationLoading,
                                onItemSelected = { selectedItem ->
                                    typeClass = selectedItem
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .background(
                                        color = colorResource(R.color.lightPurple),
                                        shape = RoundedCornerShape(8.dp)
                                    )
                            )
                        }
                    }

                    // Search button
                    Spacer(modifier = Modifier.height(16.dp))
                    GradientButton(
                        onClick = {
                            try {
                                if (from.isEmpty() || to.isEmpty() || departureDate.isEmpty() || typeClass.isEmpty()) {
                                    errorMessage = "Please fill all required fields"
                                    return@GradientButton
                                }
                                val totalPassengers = (adultPassenger.toIntOrNull() ?: 0) +
                                        (childPassenger.toIntOrNull() ?: 0)
                                if (totalPassengers <= 0) {
                                    errorMessage = "At least one passenger is required"
                                    return@GradientButton
                                }
                                val intent = Intent(context, SearchResultActivity::class.java).apply {
                                    putExtra("from", from)
                                    putExtra("to", to)
                                    putExtra("departureDate", departureDate)
                                    putExtra("typeClass", typeClass)
                                    putExtra("numPassenger", totalPassengers.toString())
                                    putExtra("user", user) // Thêm UserModel
                                }
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                Log.e(TAG, "Error starting SearchResultActivity: ${e.message}")
                                errorMessage = "Error: Unable to search flights"
                            }
                        },
                        text = "Search"
                    )

                    // Error message
                    errorMessage?.let { message ->
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = message,
                            color = Color.Red,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun YellowTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontWeight = FontWeight.SemiBold,
        color = colorResource(R.color.orange),
        fontSize = 16.sp,
        modifier = modifier
    )
}