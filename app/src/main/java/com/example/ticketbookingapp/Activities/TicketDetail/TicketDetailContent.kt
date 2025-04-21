//package com.example.ticketbookingapp.Activities.TicketDetail
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.constraintlayout.compose.ConstraintLayout
//import coil.compose.AsyncImage
//import com.example.ticketbookingapp.Domain.FlightModel
//import com.example.ticketbookingapp.R
//
//@Composable
//fun TicketDetailContent(
//    flight: FlightModel,
//    selectedSeats: String, // Thêm tham số selectedSeats
//    totalPrice: Double,    // Thêm tham số totalPrice
//    modifier: Modifier
//) {
//    Column(
//        modifier = modifier
//            .padding(24.dp)
//            .background(
//                color = colorResource(R.color.lightPurple),
//                shape = RoundedCornerShape(20.dp)
//            )
//    ) {
//        ConstraintLayout(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 8.dp)
//        ) {
//            val (logo, arrivalTxt, lineImg, fromTxt, fromShortTxt, toTxt, toShortTxt) = createRefs()
//
//            AsyncImage(
//                model = flight.AirlineLogo,
//                contentDescription = null,
//                modifier = Modifier
//                    .size(200.dp, 50.dp)
//                    .constrainAs(logo) {
//                        top.linkTo(parent.top, margin = 8.dp)
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                    },
//                contentScale = ContentScale.Fit
//            )
//            Text(
//                text = flight.ArriveTime,
//                fontSize = 12.sp,
//                fontWeight = FontWeight.Bold,
//                color = colorResource(R.color.darkPurple2),
//                modifier = Modifier.constrainAs(arrivalTxt) {
//                    top.linkTo(logo.bottom, margin = 8.dp)
//                    start.linkTo(logo.start)
//                    end.linkTo(logo.end)
//                }
//            )
//
//            Image(
//                painter = painterResource(R.drawable.line_airple_blue),
//                contentDescription = null,
//                modifier = Modifier.constrainAs(lineImg) {
//                    top.linkTo(arrivalTxt.bottom, margin = 8.dp)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                }
//            )
//
//            Text(
//                text = "from",
//                fontSize = 14.sp,
//                color = Color.Black,
//                modifier = Modifier.constrainAs(fromTxt) {
//                    top.linkTo(arrivalTxt.bottom)
//                    start.linkTo(parent.start)
//                    end.linkTo(lineImg.start)
//                }
//            )
//
//            Text(
//                text = flight.FromShort,
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black,
//                modifier = Modifier.constrainAs(fromShortTxt) {
//                    top.linkTo(fromTxt.bottom, margin = 8.dp)
//                    start.linkTo(fromTxt.start)
//                    end.linkTo(fromTxt.end)
//                    bottom.linkTo(lineImg.bottom)
//                }
//            )
//
//            Text(
//                text = "to",
//                fontSize = 14.sp,
//                color = Color.Black,
//                modifier = Modifier.constrainAs(toTxt) {
//                    top.linkTo(arrivalTxt.bottom)
//                    end.linkTo(parent.end)
//                    start.linkTo(lineImg.end)
//                }
//            )
//
//            Text(
//                text = flight.ToShort,
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black,
//                modifier = Modifier.constrainAs(toShortTxt) {
//                    top.linkTo(toTxt.bottom, margin = 8.dp)
//                    start.linkTo(toTxt.start)
//                    end.linkTo(toTxt.end)
//                    bottom.linkTo(lineImg.bottom)
//                }
//            )
//        }
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            Column(
//                modifier = Modifier
//                    .weight(0.5f)
//            ) {
//                Text(text = "From", color = Color.Black)
//                Text(text = flight.From, color = Color.Black, fontWeight = FontWeight.Bold)
//                Spacer(modifier = Modifier.height(16.dp))
//                Text(text = "Date", color = Color.Black)
//                Text(text = flight.Date, color = Color.Black, fontWeight = FontWeight.Bold)
//            }
//
//            Column(
//                modifier = Modifier
//                    .weight(0.5f)
//            ) {
//                Text(text = "To", color = Color.Black)
//                Text(text = flight.To, color = Color.Black, fontWeight = FontWeight.Bold)
//                Spacer(modifier = Modifier.height(16.dp))
//                Text(text = "Time", color = Color.Black)
//                Text(text = flight.Time, color = Color.Black, fontWeight = FontWeight.Bold)
//            }
//        }
//
//        Image(
//            painter = painterResource(R.drawable.dash_line),
//            contentDescription = null,
//            modifier = Modifier.fillMaxWidth(),
//            contentScale = ContentScale.FillWidth
//        )
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            Column(
//                modifier = Modifier
//                    .weight(0.5f)
//            ) {
//                Text(text = "Class", color = Color.Black)
//                Text(text = flight.ClassSeat, color = Color.Black, fontWeight = FontWeight.Bold)
//                Spacer(modifier = Modifier.height(16.dp))
//                Text(text = "Seats", color = Color.Black)
//                Text(
//                    text = selectedSeats.ifEmpty { "No seats selected" }, // Hiển thị danh sách ghế đã chọn
//                    color = Color.Black,
//                    fontWeight = FontWeight.Bold
//                )
//            }
//
//            Column(
//                modifier = Modifier
//                    .weight(0.5f)
//            ) {
//                Text(text = "Airlines", color = Color.Black)
//                Text(text = flight.AirlineName, color = Color.Black, fontWeight = FontWeight.Bold)
//                Spacer(modifier = Modifier.height(16.dp))
//                Text(text = "Price", color = Color.Black)
//                Text(
//                    text = "$${String.format("%.2f", totalPrice)}", // Hiển thị tổng giá
//                    color = Color.Black,
//                    fontWeight = FontWeight.Bold
//                )
//            }
//
//            Image(
//                painter = painterResource(R.drawable.qrcode),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(50.dp)
//                    .padding(start = 8.dp),
//            )
//        }
//
//        Image(
//            painter = painterResource(R.drawable.dash_line),
//            contentDescription = null,
//            modifier = Modifier.fillMaxWidth(),
//            contentScale = ContentScale.FillWidth
//        )
//
//        Image(
//            painter = painterResource(R.drawable.barcode),
//            contentDescription = null,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            contentScale = ContentScale.FillWidth
//        )
//    }
//}

package com.example.ticketbookingapp.Activities.TicketDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.example.ticketbookingapp.Domain.FlightModel
import com.example.ticketbookingapp.R

@Composable
fun TicketDetailContent(
    flight: FlightModel,
    selectedSeats: String,
    totalPrice: Double,
    bookingTime: String,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .padding(24.dp)
            .background(
                color = colorResource(R.color.lightPurple),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(16.dp)
    ) {
        // Phần logo và thông tin chuyến bay
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            val (logo, arrivalTxt, lineImg, fromTxt, fromShortTxt, toTxt, toShortTxt) = createRefs()

            if (flight.AirlineLogo.isNotBlank()) {
                AsyncImage(
                    model = flight.AirlineLogo,
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp, 50.dp)
                        .constrainAs(logo) {
                            top.linkTo(parent.top, margin = 8.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    contentScale = ContentScale.Fit,
                    onState = { state ->
                        if (state is AsyncImagePainter.State.Error) {
                            println("Error loading airline logo: ${state.result.throwable.message}")
                        }
                    }
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(200.dp, 50.dp)
                        .constrainAs(logo) {
                            top.linkTo(parent.top, margin = 8.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )
            }

            Text(
                text = flight.ArriveTime.takeIf { it.isNotBlank() } ?: "N/A",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.darkPurple2),
                modifier = Modifier.constrainAs(arrivalTxt) {
                    top.linkTo(logo.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            Image(
                painter = painterResource(R.drawable.line_airple_blue),
                contentDescription = null,
                modifier = Modifier
                    .height(20.dp)
                    .fillMaxWidth(0.5f)
                    .constrainAs(lineImg) {
                        top.linkTo(arrivalTxt.bottom, margin = 8.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.FillWidth
            )

            Text(
                text = "from",
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.constrainAs(fromTxt) {
                    top.linkTo(arrivalTxt.bottom)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(lineImg.start)
                }
            )

            Text(
                text = flight.FromShort.takeIf { it.isNotBlank() } ?: "N/A",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.constrainAs(fromShortTxt) {
                    top.linkTo(fromTxt.bottom, margin = 8.dp)
                    start.linkTo(fromTxt.start)
                    end.linkTo(fromTxt.end)
                    bottom.linkTo(lineImg.bottom)
                }
            )

            Text(
                text = "to",
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.constrainAs(toTxt) {
                    top.linkTo(arrivalTxt.bottom)
                    end.linkTo(parent.end, margin = 16.dp)
                    start.linkTo(lineImg.end)
                }
            )

            Text(
                text = flight.ToShort.takeIf { it.isNotBlank() } ?: "N/A",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.constrainAs(toShortTxt) {
                    top.linkTo(toTxt.bottom, margin = 8.dp)
                    start.linkTo(toTxt.start)
                    end.linkTo(toTxt.end)
                    bottom.linkTo(lineImg.bottom)
                }
            )
        }

        // Phần thông tin chi tiết
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = "From",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Text(
                    text = flight.From.takeIf { it.isNotBlank() } ?: "N/A",
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Date",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Text(
                    text = flight.Date.takeIf { it.isNotBlank() } ?: "N/A",
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            }

            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = "To",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Text(
                    text = flight.To.takeIf { it.isNotBlank() } ?: "N/A",
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Time",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Text(
                    text = flight.Time.takeIf { it.isNotBlank() } ?: "N/A",
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            }
        }

        Image(
            painter = painterResource(R.drawable.dash_line),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp),
            contentScale = ContentScale.FillWidth
        )

        // Phần thông tin bổ sung và giá
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = "Class",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Text(
                    text = flight.ClassSeat.takeIf { it.isNotBlank() } ?: "N/A",
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Seats",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Text(
                    text = selectedSeats.ifEmpty { "No seats selected" },
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Booking Time",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Text(
                    text = bookingTime,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            }

            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = "Airlines",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Text(
                    text = flight.AirlineName.takeIf { it.isNotBlank() } ?: "N/A",
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Price",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Text(
                    text = "$${String.format("%.2f", totalPrice)}",
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            }

            Image(
                painter = painterResource(R.drawable.qrcode),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .padding(start = 8.dp),
                contentScale = ContentScale.Fit
            )
        }

        Image(
            painter = painterResource(R.drawable.dash_line),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp),
            contentScale = ContentScale.FillWidth
        )

        Image(
            painter = painterResource(R.drawable.barcode),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentScale = ContentScale.FillWidth
        )
    }
}