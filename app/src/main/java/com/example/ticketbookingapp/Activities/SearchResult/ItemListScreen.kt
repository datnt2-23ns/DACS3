//package com.example.ticketbookingapp.Activities.SearchResult
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.itemsIndexed
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.constraintlayout.compose.ConstraintLayout
//import com.example.ticketbookingapp.Domain.FlightModel
//import com.example.ticketbookingapp.R
//import com.example.ticketbookingapp.ViewModel.MainViewModel
//import kotlinx.coroutines.delay
//
//@Composable
//fun ItemListScreen(
//    from: String,
//    to: String,
//    departureDate: String,
//    returnDate: String,
//    typeClass: String,
//    numPassenger: Int,
//    viewModel: MainViewModel,
//    onBackClick: () -> Unit
//) {
//    // Trạng thái
//    val items by viewModel.loadFiltered(from, to, departureDate, typeClass, numPassenger).observeAsState(emptyList())
//    var isLoading by remember { mutableStateOf(true) }
//    var errorMessage by remember { mutableStateOf<String?>(null) }
//
//    // Cập nhật trạng thái sau khi truy vấn
//    LaunchedEffect(items) {
//        // Giảm thời gian chờ từ 5s xuống 2s
//        delay(2000)
//        isLoading = false
//        println("Items received: ${items.size}, data: $items")
//        if (items.isEmpty()) {
//            errorMessage = "No flights found for $from to $to on $departureDate ($typeClass) with $numPassenger passengers"
//        } else {
//            errorMessage = null
//        }
//    }
//
//    // Giao diện
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(color = colorResource(R.color.darkPurple2))
//    ) {
//        Column {
//            // Header
//            ConstraintLayout(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(color = colorResource(R.color.darkPurple2))
//                    .padding(top = 36.dp, start = 16.dp, end = 16.dp)
//            ) {
//                val (backBtn, headerTitle) = createRefs()
//
//                Image(
//                    painter = painterResource(R.drawable.back),
//                    contentDescription = "Back button",
//                    modifier = Modifier
//                        .clickable { onBackClick() }
//                        .constrainAs(backBtn) {
//                            top.linkTo(parent.top)
//                            start.linkTo(parent.start)
//                        }
//                )
//
//                Text(
//                    text = "Search Result",
//                    textAlign = TextAlign.Center,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 18.sp,
//                    color = Color.White,
//                    modifier = Modifier
//                        .padding(start = 8.dp)
//                        .constrainAs(headerTitle) {
//                            start.linkTo(backBtn.end, margin = 8.dp)
//                            top.linkTo(backBtn.top)
//                            bottom.linkTo(backBtn.bottom)
//                        }
//                )
//            }
//
//            // Nội dung
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(top = 80.dp),
//                contentAlignment = Alignment.Center
//            ) {
//                when {
//                    isLoading -> {
//                        CircularProgressIndicator()
//                    }
//                    errorMessage != null -> {
//                        Text(
//                            text = errorMessage!!,
//                            color = Color.White,
//                            fontSize = 16.sp,
//                            textAlign = TextAlign.Center,
//                            modifier = Modifier.padding(16.dp)
//                        )
//                    }
//                    else -> {
//                        LazyColumn(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(horizontal = 16.dp)
//                        ) {
//                            itemsIndexed(items) { index, item ->
//                                FlightItem(
//                                    item = item,
//                                    index = index,
//                                    numPassenger = numPassenger
//                                )
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}

//package com.example.ticketbookingapp.Activities.SearchResult
//
//import android.content.Intent
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.itemsIndexed
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.constraintlayout.compose.ConstraintLayout
//import com.example.ticketbookingapp.Activities.SeatSelect.SeatSelectActivity
//import com.example.ticketbookingapp.Domain.FlightModel
//import com.example.ticketbookingapp.Domain.UserModel
//import com.example.ticketbookingapp.R
//import com.example.ticketbookingapp.ViewModel.MainViewModel
//import kotlinx.coroutines.delay
//
//@Composable
//fun ItemListScreen(
//    from: String,
//    to: String,
//    departureDate: String,
//    returnDate: String,
//    typeClass: String,
//    numPassenger: Int,
//    user: UserModel,
//    viewModel: MainViewModel,
//    onBackClick: () -> Unit
//) {
//    // Trạng thái
//    val items by viewModel.loadFiltered(from, to, departureDate, typeClass, numPassenger).observeAsState(emptyList())
//    var isLoading by remember { mutableStateOf(true) }
//    var errorMessage by remember { mutableStateOf<String?>(null) }
//
//    // Cập nhật trạng thái sau khi truy vấn
//    LaunchedEffect(items) {
//        delay(2000)
//        isLoading = false
//        println("Items received: ${items.size}, data: $items")
//        if (items.isEmpty()) {
//            errorMessage = "No flights found for $from to $to on $departureDate ($typeClass) with $numPassenger passengers"
//        } else {
//            errorMessage = null
//        }
//    }
//
//    // Giao diện
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(color = colorResource(R.color.darkPurple2))
//    ) {
//        Column {
//            // Header
//            ConstraintLayout(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(color = colorResource(R.color.darkPurple2))
//                    .padding(top = 36.dp, start = 16.dp, end = 16.dp)
//            ) {
//                val (backBtn, headerTitle) = createRefs()
//
//                Image(
//                    painter = painterResource(R.drawable.back),
//                    contentDescription = "Back button",
//                    modifier = Modifier
//                        .clickable { onBackClick() }
//                        .constrainAs(backBtn) {
//                            top.linkTo(parent.top)
//                            start.linkTo(parent.start)
//                        }
//                )
//
//                Text(
//                    text = "Search Result",
//                    textAlign = TextAlign.Center,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 18.sp,
//                    color = Color.White,
//                    modifier = Modifier
//                        .padding(start = 8.dp)
//                        .constrainAs(headerTitle) {
//                            start.linkTo(backBtn.end, margin = 8.dp)
//                            top.linkTo(backBtn.top)
//                            bottom.linkTo(backBtn.bottom)
//                        }
//                )
//            }
//
//            // Nội dung
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(top = 80.dp),
//                contentAlignment = Alignment.Center
//            ) {
//                when {
//                    isLoading -> {
//                        CircularProgressIndicator()
//                    }
//                    errorMessage != null -> {
//                        Text(
//                            text = errorMessage!!,
//                            color = Color.White,
//                            fontSize = 16.sp,
//                            textAlign = TextAlign.Center,
//                            modifier = Modifier.padding(16.dp)
//                        )
//                    }
//                    else -> {
//                        LazyColumn(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(horizontal = 16.dp)
//                        ) {
//                            itemsIndexed(items) { index, item ->
//                                FlightItem(
//                                    item = item,
//                                    index = index,
//                                    numPassenger = numPassenger,
//                                    user = user
//                                )
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun FlightItem(
//    item: FlightModel,
//    index: Int,
//    numPassenger: Int,
//    user: UserModel
//) {
//    val context = LocalContext.current
//
//    // Gán flightId nếu trống
//    val flight = item.copy().apply {
//        if (FlightId.isEmpty()) {
//            FlightId = "flight_${item.AirlineName}_${item.Date.replace(" ", "_")}_${item.Time.replace(":", "")}"
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp)
//            .background(colorResource(R.color.lightPurple), shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
//            .clickable {
//                val intent = Intent(context, SeatSelectActivity::class.java).apply {
//                    putExtra("flight", flight)
//                    putExtra("user", user)
//                    putExtra("numPassenger", numPassenger.toString())
//                }
//                context.startActivity(intent)
//            }
//            .padding(16.dp)
//    ) {
//        Text(
//            text = "${flight.AirlineName} - ${flight.From} to ${flight.To}",
//            color = Color.Black,
//            fontWeight = FontWeight.Bold,
//            fontSize = 16.sp
//        )
//        Spacer(modifier = Modifier.height(4.dp))
//        Text(
//            text = "Date: ${flight.Date}",
//            color = Color.Black,
//            fontSize = 14.sp
//        )
//        Text(
//            text = "Time: ${flight.Time}",
//            color = Color.Black,
//            fontSize = 14.sp
//        )
//        Text(
//            text = "Class: ${flight.TypeClass}",
//            color = Color.Black,
//            fontSize = 14.sp
//        )
//        Text(
//            text = "Price: $${flight.Price}",
//            color = colorResource(R.color.orange),
//            fontSize = 14.sp
//        )
//    }
//}

package com.example.ticketbookingapp.Activities.SearchResult

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ticketbookingapp.Domain.FlightModel
import com.example.ticketbookingapp.Domain.UserModel
import com.example.ticketbookingapp.R
import com.example.ticketbookingapp.ViewModel.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun ItemListScreen(
    from: String,
    to: String,
    departureDate: String,
    returnDate: String,
    typeClass: String,
    numPassenger: Int,
    user: UserModel,
    viewModel: MainViewModel,
    onBackClick: () -> Unit
) {
    // Trạng thái
    val items by viewModel.loadFiltered(from, to, departureDate, typeClass, numPassenger).observeAsState(emptyList())
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Cập nhật trạng thái sau khi truy vấn
    LaunchedEffect(items) {
        // Giảm thời gian chờ từ 5s xuống 2s
        delay(2000)
        isLoading = false
        println("Items received: ${items.size}, data: $items")
        if (items.isEmpty()) {
            errorMessage = "No flights found for $from to $to on $departureDate ($typeClass) with $numPassenger passengers"
        } else {
            errorMessage = null
        }
    }

    // Giao diện
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.darkPurple2))
    ) {
        Column {
            // Header
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(R.color.darkPurple2))
                    .padding(top = 36.dp, start = 16.dp, end = 16.dp)
            ) {
                val (backBtn, headerTitle) = createRefs()

                Image(
                    painter = painterResource(R.drawable.back),
                    contentDescription = "Back button",
                    modifier = Modifier
                        .clickable { onBackClick() }
                        .constrainAs(backBtn) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        }
                )

                Text(
                    text = "Search Result",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .constrainAs(headerTitle) {
                            start.linkTo(backBtn.end, margin = 8.dp)
                            top.linkTo(backBtn.top)
                            bottom.linkTo(backBtn.bottom)
                        }
                )
            }

            // Nội dung
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 80.dp),
                contentAlignment = Alignment.Center
            ) {
                when {
                    isLoading -> {
                        CircularProgressIndicator()
                    }
                    errorMessage != null -> {
                        Text(
                            text = errorMessage!!,
                            color = Color.White,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    else -> {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            itemsIndexed(items) { index, item ->
                                FlightItem(
                                    item = item,
                                    index = index,
                                    numPassenger = numPassenger,
                                    user = user
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}