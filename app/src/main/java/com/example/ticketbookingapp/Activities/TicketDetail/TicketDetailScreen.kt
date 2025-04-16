//package com.example.ticketbookingapp.Activities.TicketDetail
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.unit.dp
//import androidx.constraintlayout.compose.ConstraintLayout
//import com.example.ticketbookingapp.Activities.SeatSelect.TicketDetailHeader
//import com.example.ticketbookingapp.Activities.Splash.GradientButton
//import com.example.ticketbookingapp.Domain.FlightModel
//import com.example.ticketbookingapp.R
//
//@Composable
//fun TicketDetailScreen(
//    flight: FlightModel,
//    onBackClick: () -> Unit,
//    onDownloadTicketClick: () -> Unit
//) {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(colorResource(R.color.darkPurple2))
//    ) {
//        Column(
//            modifier = Modifier
//                .verticalScroll(rememberScrollState())
//                .fillMaxSize()
//                .background(colorResource(R.color.darkPurple2))
//        ) {
//            ConstraintLayout(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(colorResource(R.color.darkPurple2))
//            ) {
//                val (topSection, ticketDetail) = createRefs()
//
//                TicketDetailHeader(onBackClick = onBackClick, Modifier.constrainAs(topSection) {
//                    top.linkTo(parent.top)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                })
//
//                TicketDetailContent(flight = flight, Modifier.constrainAs(ticketDetail) {
//                    top.linkTo(parent.top, margin = 110.dp)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                })
//            }
//
//            GradientButton(onClick = {}, text = "Download Ticket")
//        }
//    }
//}

package com.example.ticketbookingapp.Activities.TicketDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ticketbookingapp.Activities.SeatSelect.TicketDetailHeader
import com.example.ticketbookingapp.Activities.Splash.GradientButton
import com.example.ticketbookingapp.Domain.FlightModel
import com.example.ticketbookingapp.R

@Composable
fun TicketDetailScreen(
    flight: FlightModel,
    selectedSeats: String,          // Thêm tham số selectedSeats
    totalPrice: Double,            // Thêm tham số totalPrice
    onBackClick: () -> Unit,
    onDownloadTicketClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.darkPurple2))
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(colorResource(R.color.darkPurple2))
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(R.color.darkPurple2))
            ) {
                val (topSection, ticketDetail) = createRefs()

                TicketDetailHeader(
                    onBackClick = onBackClick,
                    modifier = Modifier.constrainAs(topSection) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )

                TicketDetailContent(
                    flight = flight,
                    selectedSeats = selectedSeats, // Truyền selectedSeats
                    totalPrice = totalPrice,      // Truyền totalPrice
                    modifier = Modifier.constrainAs(ticketDetail) {
                        top.linkTo(parent.top, margin = 110.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )
            }

            GradientButton(
                onClick = onDownloadTicketClick,
                text = "Download Ticket"
            )
        }
    }
}