//package com.example.ticketbookingapp.Activities.Dashboard
//
//import android.content.Context
//import android.icu.util.Calendar
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
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
//import com.example.ticketbookingapp.R
//import java.text.SimpleDateFormat
//import java.util.Locale
//
//@Composable
//fun DatePickerScreen(modifier: Modifier = Modifier) {
//    val context = LocalContext.current
//    val dateFormat = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) }
//    val departureCalendar = remember { Calendar.getInstance() }
//    val returnCalendar = remember { Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, 1) } }
//    var departureDate by remember { mutableStateOf(dateFormat.format(departureCalendar.time)) }
//    var returnDate by remember { mutableStateOf(dateFormat.format(returnCalendar.time)) }
//
//    Row {
//        DatePickerItem(
//            modifier,
//            dateText = departureDate,
//            onDataSelected = { selectedDate ->
//                departureDate = selectedDate
//            },
//            dateFormat = dateFormat,
//            calendar = departureCalendar,
//            context = context
//        )
//
//        Spacer(modifier = Modifier.width(16.dp))
//        DatePickerItem(
//            modifier,
//            dateText = returnDate,
//            onDataSelected = { selectedDate ->
//                returnDate = selectedDate
//            },
//            dateFormat = dateFormat,
//            calendar = returnCalendar,
//            context = context
//        )
//    }
//}
//
//@Composable
//fun DatePickerItem(
//    modifier: Modifier = Modifier,
//    dateText: String,
//    onDataSelected: (String) -> Unit,
//    dateFormat: SimpleDateFormat,
//    calendar: Calendar,
//    context: Context
//) {
//    Row(
//        modifier = modifier
//            .height(60.dp)
//            .padding(top = 8.dp)
//            .background(
//                color = colorResource(R.color.lightPurple),
//                shape = RoundedCornerShape(8.dp)
//            )
//            .clickable {
//                showDataPickerDialog(context, calendar, dateFormat, onDataSelected)
//            },
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Image(
//            painter = painterResource(R.drawable.calendar_ic),
//            contentDescription = null,
//            modifier = Modifier
//                .padding(start = 8.dp)
//                .size(24.dp)
//        )
//        Text(
//            text = dateText,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 8.dp),
//            color = Color.Black,
//            fontWeight = FontWeight.Bold,
//            textAlign = TextAlign.Center
//        )
//    }
//}
//
//fun showDataPickerDialog(
//    context: Context,
//    calendar: Calendar,
//    dateFormat: SimpleDateFormat,
//    onDataSelected: (String) -> Unit
//) {
//    val year = calendar.get(Calendar.YEAR)
//    val month = calendar.get(Calendar.MONTH)
//    val day = calendar.get(Calendar.DAY_OF_MONTH)
//
//    android.app.DatePickerDialog(context, { _, selectedYear, selectedMonth, selectedDay ->
//        calendar.set(selectedYear, selectedMonth, selectedDay)
//        val formattedDate = dateFormat.format(calendar.time)
//        onDataSelected(formattedDate)
//    }, year, month, day).show()
//}

package com.example.ticketbookingapp.Activities.Dashboard

import android.content.Context
import android.icu.util.Calendar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ticketbookingapp.R
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun DatePickerScreen(
    modifier: Modifier = Modifier,
    onDepartureSelected: (String) -> Unit, // Callback cho ngày đi
    onReturnSelected: (String) -> Unit // Callback cho ngày về
) {
    val context = LocalContext.current
    // Định dạng ngày khớp với database (chữ thường)
    val dateFormat = remember { SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()) }
    val departureCalendar = remember { Calendar.getInstance() }
    val returnCalendar = remember { Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, 1) } }
    var departureDate by remember { mutableStateOf("") }
    var returnDate by remember { mutableStateOf("") }

    Row {
        DatePickerItem(
            modifier = Modifier.weight(1f),
            dateText = departureDate.ifEmpty { "Select departure" },
            onDataSelected = { selectedDate ->
                departureDate = selectedDate.toLowerCase(Locale.getDefault())
                onDepartureSelected(departureDate)
            },
            dateFormat = dateFormat,
            calendar = departureCalendar,
            context = context
        )

        Spacer(modifier = Modifier.width(16.dp))

        DatePickerItem(
            modifier = Modifier.weight(1f),
            dateText = returnDate.ifEmpty { "Select return" },
            onDataSelected = { selectedDate ->
                returnDate = selectedDate.toLowerCase(Locale.getDefault())
                onReturnSelected(returnDate)
            },
            dateFormat = dateFormat,
            calendar = returnCalendar,
            context = context
        )
    }
}

@Composable
fun DatePickerItem(
    modifier: Modifier = Modifier,
    dateText: String,
    onDataSelected: (String) -> Unit,
    dateFormat: SimpleDateFormat,
    calendar: Calendar,
    context: Context
) {
    Row(
        modifier = modifier
            .height(60.dp)
            .padding(top = 8.dp)
            .background(
                color = colorResource(R.color.lightPurple),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                showDataPickerDialog(context, calendar, dateFormat, onDataSelected)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.calendar_ic),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 8.dp)
                .size(24.dp)
        )
        Text(
            text = dateText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

fun showDataPickerDialog(
    context: Context,
    calendar: Calendar,
    dateFormat: SimpleDateFormat,
    onDataSelected: (String) -> Unit
) {
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    android.app.DatePickerDialog(context, { _, selectedYear, selectedMonth, selectedDay ->
        calendar.set(selectedYear, selectedMonth, selectedDay)
        val formattedDate = dateFormat.format(calendar.time).toLowerCase(Locale.getDefault())
        onDataSelected(formattedDate)
    }, year, month, day).show()
}