//package com.example.ticketbookingapp.Activities.SearchResult
//
//import android.content.Intent
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.itemsIndexed
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.constraintlayout.compose.ConstraintLayout
//import coil.compose.AsyncImage
//import com.example.ticketbookingapp.Activities.SeatSelect.SeatSelectActivity
//import com.example.ticketbookingapp.Domain.FlightModel
//import com.example.ticketbookingapp.R
//import com.example.ticketbookingapp.ViewModel.MainViewModel
//
//@Composable
//fun ItemListScreen(
//    from: String,
//    to: String,
//    departureDate: String,
//    returnDate: String,
//    typeClass: String,
//    numPassenger: Int, // Nhận numPassenger như tham số
//    viewModel: MainViewModel,
//    onBackClick: () -> Unit
//) {
//    val items by viewModel.loadFiltered(from, to, departureDate, typeClass).observeAsState(emptyList())
//    var isLoading by remember { mutableStateOf(true) }
//    val context = LocalContext.current
//
//    // Cập nhật trạng thái loading
//    if (items.isNotEmpty() || items.isEmpty()) {
//        isLoading = false
//    }
//
//    ConstraintLayout(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(color = colorResource(R.color.darkPurple2))
//            .padding(top = 36.dp, start = 16.dp, end = 16.dp)
//    ) {
//        val (backBtn, headerTitle) = createRefs()
//
//        Image(
//            painter = painterResource(R.drawable.back),
//            contentDescription = "Back button",
//            modifier = Modifier
//                .clickable { onBackClick() }
//                .constrainAs(backBtn) {
//                    top.linkTo(parent.top)
//                    start.linkTo(parent.start)
//                }
//        )
//
//        Text(
//            text = "Search Result",
//            textAlign = TextAlign.Center,
//            fontWeight = FontWeight.Bold,
//            fontSize = 18.sp,
//            color = Color.White,
//            modifier = Modifier
//                .padding(start = 8.dp)
//                .constrainAs(headerTitle) {
//                    start.linkTo(backBtn.end, margin = 8.dp)
//                    top.linkTo(backBtn.top)
//                    bottom.linkTo(backBtn.bottom)
//                }
//        )
//    }
//
//    // Hiển thị giao diện
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        when {
//            isLoading -> {
//                CircularProgressIndicator()
//            }
//
//            items.isEmpty() -> {
//                Text(
//                    text = "No flights found for $from to $to on $departureDate ($typeClass)",
//                    color = Color.White,
//                    fontSize = 16.sp,
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier.padding(16.dp)
//                )
//            }
//
//            else -> {
//                LazyColumn(
//                    modifier = Modifier
//                        .padding(horizontal = 16.dp)
//                        .padding(top = 60.dp)
//                ) {
//                    itemsIndexed(items as List<FlightModel>) { index, item ->
//                        FlightItem(
//                            item = item,
//                            index = index,
//                            numPassenger = numPassenger
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun FlightItem(item: FlightModel, index: Int, numPassenger: Int) {
//    val context = LocalContext.current
//
//    ConstraintLayout(
//        modifier = Modifier
//            .padding(horizontal = 16.dp, vertical = 8.dp)
//            .fillMaxWidth()
//            .clickable {
//                val intent = Intent(context, SeatSelectActivity::class.java).apply {
//                    putExtra("flight", item)
//                    putExtra("numPassenger", numPassenger.toString())
//                }
//                context.startActivity(intent)
//            }
//            .background(
//                color = colorResource(R.color.lightPurple),
//                shape = RoundedCornerShape(15.dp)
//            )
//    ) {
//        val (
//            logo, timeTxt, airplaneIcon, dashLine,
//            priceTxt, seatIcon, classTxt,
//            fromTxt, fromShortTxt, toTxt, toShortTxt
//        ) = createRefs()
//
//        AsyncImage(
//            model = item.AirlineLogo,
//            contentDescription = "Airline logo",
//            contentScale = ContentScale.Fit,
//            modifier = Modifier
//                .size(width = 120.dp, height = 40.dp)
//                .constrainAs(logo) {
//                    start.linkTo(parent.start)
//                    top.linkTo(parent.top)
//                    end.linkTo(parent.end)
//                }
//        )
//
//        Text(
//            text = item.ArriveTime,
//            textAlign = TextAlign.Center,
//            fontWeight = FontWeight.Bold,
//            fontSize = 12.sp,
//            color = colorResource(R.color.darkPurple2),
//            modifier = Modifier
//                .padding(top = 8.dp)
//                .constrainAs(timeTxt) {
//                    start.linkTo(parent.start)
//                    top.linkTo(logo.bottom)
//                    end.linkTo(parent.end)
//                }
//        )
//
//        Image(
//            painter = painterResource(R.drawable.line_airple_blue),
//            contentDescription = "Flight path icon",
//            modifier = Modifier
//                .padding(top = 8.dp)
//                .constrainAs(airplaneIcon) {
//                    start.linkTo(parent.start)
//                    top.linkTo(timeTxt.bottom)
//                    end.linkTo(parent.end)
//                }
//        )
//
//        Image(
//            painter = painterResource(R.drawable.dash_line),
//            contentDescription = "Separator line",
//            modifier = Modifier
//                .padding(top = 8.dp)
//                .constrainAs(dashLine) {
//                    start.linkTo(parent.start)
//                    top.linkTo(airplaneIcon.bottom)
//                    end.linkTo(parent.end)
//                }
//        )
//
//        Text(
//            text = "$${String.format("%.2f", item.Price)}",
//            fontWeight = FontWeight.SemiBold,
//            fontSize = 25.sp,
//            color = colorResource(R.color.orange),
//            modifier = Modifier
//                .padding(8.dp)
//                .constrainAs(priceTxt) {
//                    top.linkTo(dashLine.bottom)
//                    bottom.linkTo(parent.bottom)
//                    end.linkTo(parent.end)
//                }
//        )
//
//        Image(
//            painter = painterResource(R.drawable.seat_black_ic),
//            contentDescription = "Seat icon",
//            modifier = Modifier
//                .padding(8.dp)
//                .constrainAs(seatIcon) {
//                    start.linkTo(parent.start)
//                    top.linkTo(dashLine.bottom)
//                    bottom.linkTo(parent.bottom)
//                }
//        )
//
//        Text(
//            text = item.ClassSeat,
//            fontWeight = FontWeight.SemiBold,
//            fontSize = 14.sp,
//            color = colorResource(R.color.darkPurple2),
//            modifier = Modifier
//                .constrainAs(classTxt) {
//                    start.linkTo(seatIcon.end, margin = 4.dp)
//                    top.linkTo(seatIcon.top)
//                    bottom.linkTo(seatIcon.bottom)
//                }
//        )
//
//        Text(
//            text = item.From,
//            fontSize = 14.sp,
//            color = Color.Black,
//            modifier = Modifier
//                .padding(start = 16.dp)
//                .constrainAs(fromTxt) {
//                    top.linkTo(timeTxt.bottom)
//                    start.linkTo(parent.start)
//                }
//        )
//
//        Text(
//            text = item.FromShort,
//            fontSize = 18.sp,
//            fontWeight = FontWeight.SemiBold,
//            color = Color.Black,
//            modifier = Modifier
//                .padding(start = 16.dp)
//                .constrainAs(fromShortTxt) {
//                    top.linkTo(fromTxt.bottom)
//                    start.linkTo(fromTxt.start)
//                    end.linkTo(fromTxt.end)
//                }
//        )
//
//        Text(
//            text = item.To,
//            fontSize = 14.sp,
//            color = Color.Black,
//            modifier = Modifier
//                .padding(end = 16.dp)
//                .constrainAs(toTxt) {
//                    top.linkTo(timeTxt.bottom)
//                    end.linkTo(parent.end)
//                }
//        )
//
//        Text(
//            text = item.ToShort,
//            fontSize = 18.sp,
//            fontWeight = FontWeight.SemiBold,
//            color = Color.Black,
//            modifier = Modifier
//                .padding(end = 16.dp)
//                .constrainAs(toShortTxt) {
//                    top.linkTo(toTxt.bottom)
//                    start.linkTo(toTxt.start)
//                    end.linkTo(toTxt.end)
//                }
//        )
//    }
//}

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
//        // Chờ tối đa 5 giây
//        delay(5000)
//        isLoading = false
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
                                    numPassenger = numPassenger
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}