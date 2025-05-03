//package com.example.ticketbookingapp.Activities.Admin
//
//import android.widget.Toast
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.ticketbookingapp.Activities.Splash.GradientButton
//import com.example.ticketbookingapp.Domain.FlightModel
//import com.example.ticketbookingapp.Domain.UserModel
//import com.example.ticketbookingapp.R
//import com.example.ticketbookingapp.ViewModel.AdminViewModel
//import kotlinx.coroutines.launch
//
//@Composable
//fun AdminFlightManagementScreen(
//    adminViewModel: AdminViewModel,
//    user: UserModel,
//    onBackToDashboard: () -> Unit
//) {
//    var isLoading by remember { mutableStateOf(false) }
//    val coroutineScope = rememberCoroutineScope()
//    val flights by adminViewModel.flights.collectAsState()
//    val context = LocalContext.current
//    val TAG = "AdminFlightManagementScreen"
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(colorResource(R.color.darkPurple2))
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(32.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Top
//        ) {
//            Text(
//                text = "Quản Lý Chuyến Bay",
//                fontSize = 32.sp,
//                color = Color.White
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            Text(
//                text = "Chào, ${user.fullName}",
//                fontSize = 20.sp,
//                color = Color.White
//            )
//            Spacer(modifier = Modifier.height(32.dp))
//
//            flights.forEach { flight ->
//                FlightItem(flight = flight, onDelete = {
//                    coroutineScope.launch {
//                        isLoading = true
//                        try {
//                            adminViewModel.deleteFlight(flight.FlightId) // Sử dụng FlightId
//                            Toast.makeText(context, "Xóa chuyến bay thành công", Toast.LENGTH_SHORT).show()
//                        } catch (e: Exception) {
//                            Toast.makeText(context, "Lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
//                        } finally {
//                            isLoading = false
//                        }
//                    }
//                })
//            }
//
//            Spacer(modifier = Modifier.height(32.dp))
//            GradientButton(
//                onClick = { /* TODO: Mở form thêm chuyến bay */ },
//                text = "Thêm Chuyến Bay",
//                padding = 0
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            GradientButton(
//                onClick = { onBackToDashboard() },
//                text = "Quay Lại Dashboard",
//                padding = 0
//            )
//        }
//
//        if (isLoading) {
//            CircularProgressIndicator(
//                modifier = Modifier.align(Alignment.Center)
//            )
//        }
//    }
//}
//
//@Composable
//fun FlightItem(flight: FlightModel, onDelete: () -> Unit) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//            .background(colorResource(R.color.darkPurple), shape = MaterialTheme.shapes.medium),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(text = "${flight.AirlineName} (${flight.FlightId})", color = Color.White)
//            Text(text = "${flight.From} -> ${flight.To}", color = Color.White)
//            Text(text = "Ngày: ${flight.Date}", color = Color.White)
//        }
//        Button(onClick = onDelete) {
//            Text("Xóa")
//        }
//    }
//}

//package com.example.ticketbookingapp.Activities.Admin
//
//import android.widget.Toast
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalConfiguration
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.window.Dialog
//import com.example.ticketbookingapp.Domain.FlightModel
//import com.example.ticketbookingapp.Domain.UserModel
//import com.example.ticketbookingapp.R
//import com.example.ticketbookingapp.ViewModel.AdminViewModel
//import kotlinx.coroutines.launch
//import java.text.SimpleDateFormat
//import java.util.*
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AdminFlightManagementScreen(
//    adminViewModel: AdminViewModel,
//    user: UserModel,
//    onBackToDashboard: () -> Unit
//) {
//    var isLoading by remember { mutableStateOf(false) }
//    var showAddFlightDialog by remember { mutableStateOf(false) }
//    var showEditFlightDialog by remember { mutableStateOf<FlightModel?>(null) }
//    val coroutineScope = rememberCoroutineScope()
//    val flights by adminViewModel.flights.collectAsState()
//    val context = LocalContext.current
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(colorResource(R.color.darkPurple2))
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp), // Giảm padding để có thêm không gian
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Top
//        ) {
//            Text(
//                text = "Quản Lý Chuyến Bay",
//                fontSize = 32.sp,
//                color = Color.White
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            Text(
//                text = "Chào, ${user.fullName}",
//                fontSize = 20.sp,
//                color = Color.White
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Giới hạn chiều cao LazyColumn để dành không gian cho các nút
//            LazyColumn(
//                modifier = Modifier
//                    .weight(1f, fill = false) // Không cho phép chiếm hết không gian
//                    .fillMaxWidth(),
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                items(flights) { flight ->
//                    FlightItem(
//                        flight = flight,
//                        onEdit = { showEditFlightDialog = flight },
//                        onDelete = {
//                            coroutineScope.launch {
//                                isLoading = true
//                                try {
//                                    adminViewModel.deleteFlight(flight.FlightId)
//                                    Toast.makeText(context, "Xóa chuyến bay thành công", Toast.LENGTH_SHORT).show()
//                                } catch (e: Exception) {
//                                    Toast.makeText(context, "Lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
//                                } finally {
//                                    isLoading = false
//                                }
//                            }
//                        }
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//            // Nút "Thêm Chuyến Bay" sử dụng Button tiêu chuẩn
//            Button(
//                onClick = { showAddFlightDialog = true },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(48.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFF6200EE), // Màu tím nổi bật
//                    contentColor = Color.White
//                )
//            ) {
//                Text("Thêm Chuyến Bay", fontSize = 16.sp)
//            }
//            Spacer(modifier = Modifier.height(8.dp))
//            // Nút "Quay Lại Dashboard"
//            Button(
//                onClick = { onBackToDashboard() },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(48.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFF03DAC5), // Màu xanh để phân biệt
//                    contentColor = Color.White
//                )
//            ) {
//                Text("Quay Lại Dashboard", fontSize = 16.sp)
//            }
//        }
//
//        if (isLoading) {
//            CircularProgressIndicator(
//                modifier = Modifier.align(Alignment.Center)
//            )
//        }
//
//        if (showAddFlightDialog) {
//            AddFlightDialog(
//                onDismiss = { showAddFlightDialog = false },
//                onAddFlight = { newFlight ->
//                    coroutineScope.launch {
//                        isLoading = true
//                        try {
//                            adminViewModel.addFlight(newFlight)
//                            Toast.makeText(context, "Thêm chuyến bay thành công", Toast.LENGTH_SHORT).show()
//                        } catch (e: Exception) {
//                            Toast.makeText(context, "Lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
//                        } finally {
//                            isLoading = false
//                            showAddFlightDialog = false
//                        }
//                    }
//                }
//            )
//        }
//
//        showEditFlightDialog?.let { flight ->
//            EditFlightDialog(
//                flight = flight,
//                onDismiss = { showEditFlightDialog = null },
//                onUpdateFlight = { updatedFlight ->
//                    coroutineScope.launch {
//                        isLoading = true
//                        try {
//                            adminViewModel.updateFlight(updatedFlight)
//                            Toast.makeText(context, "Cập nhật chuyến bay thành công", Toast.LENGTH_SHORT).show()
//                        } catch (e: Exception) {
//                            Toast.makeText(context, "Lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
//                        } finally {
//                            isLoading = false
//                            showEditFlightDialog = null
//                        }
//                    }
//                }
//            )
//        }
//    }
//}
//
//@Composable
//fun FlightItem(flight: FlightModel, onEdit: () -> Unit, onDelete: () -> Unit) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//            .background(colorResource(R.color.darkPurple), shape = MaterialTheme.shapes.medium),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(text = "${flight.AirlineName} (${flight.FlightId})", color = Color.White)
//            Text(text = "${flight.From} (${flight.FromShort}) -> ${flight.To} (${flight.ToShort})", color = Color.White)
//            Text(text = "Ngày: ${flight.Date}", color = Color.White)
//            Text(text = "Giờ: ${flight.Time}", color = Color.White)
//            Text(text = "Thời gian bay: ${flight.ArriveTime}", color = Color.White)
//            Text(text = "Số ghế: ${flight.NumberSeat}", color = Color.White)
//            Text(text = "Giá vé: $${String.format("%.2f", flight.Price)}", color = Color.White)
//            Text(text = "Loại ghế: ${flight.ClassSeat}", color = Color.White)
//            Text(text = "Loại vé: ${flight.TypeClass}", color = Color.White)
//        }
//        Row {
//            Button(onClick = onEdit) {
//                Text("Sửa")
//            }
//            Spacer(modifier = Modifier.width(8.dp))
//            Button(onClick = onDelete) {
//                Text("Xóa")
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AddFlightDialog(onDismiss: () -> Unit, onAddFlight: (FlightModel) -> Unit) {
//    var flightId by remember { mutableStateOf("") }
//    var airlineName by remember { mutableStateOf("") }
//    var airlineLogo by remember { mutableStateOf("") }
//    var from by remember { mutableStateOf("") }
//    var to by remember { mutableStateOf("") }
//    var fromShort by remember { mutableStateOf("") }
//    var toShort by remember { mutableStateOf("") }
//    var date by remember { mutableStateOf("") }
//    var time by remember { mutableStateOf("") }
//    var arriveTime by remember { mutableStateOf("") }
//    var price by remember { mutableStateOf("") }
//    var numberSeat by remember { mutableStateOf("") }
//    var classSeat by remember { mutableStateOf("") }
//    var typeClass by remember { mutableStateOf("") }
//    var errorMessage by remember { mutableStateOf<String?>(null) }
//    var validateTrigger by remember { mutableStateOf(false) }
//
//    val validLocations = listOf(
//        "LosAngles", "NewYork", "San Francisco", "Washington, D.C", "Boston",
//        "Philadelphia", "Chicago", "Las Vegas", "Miami", "Seattle"
//    )
//    val validTypeClasses = listOf("Economy", "Business", "First")
//    val validClassSeats = listOf("Economy class", "Business class", "First class")
//    val context = LocalContext.current
//
//    LaunchedEffect(validateTrigger) {
//        if (validateTrigger) {
//            when {
//                flightId.isBlank() -> {
//                    errorMessage = "Mã chuyến bay không được để trống"
//                    Toast.makeText(context, "Mã chuyến bay không được để trống", Toast.LENGTH_SHORT).show()
//                }
//                airlineName.isBlank() -> {
//                    errorMessage = "Tên hãng bay không được để trống"
//                    Toast.makeText(context, "Tên hãng bay không được để trống", Toast.LENGTH_SHORT).show()
//                }
//                airlineLogo.isBlank() -> {
//                    errorMessage = "Logo hãng bay không được để trống"
//                    Toast.makeText(context, "Logo hãng bay không được để trống", Toast.LENGTH_SHORT).show()
//                }
//                from !in validLocations -> {
//                    errorMessage = "Điểm đi không hợp lệ"
//                    Toast.makeText(context, "Điểm đi không hợp lệ", Toast.LENGTH_SHORT).show()
//                }
//                to !in validLocations -> {
//                    errorMessage = "Điểm đến không hợp lệ"
//                    Toast.makeText(context, "Điểm đến không hợp lệ", Toast.LENGTH_SHORT).show()
//                }
//                from == to -> {
//                    errorMessage = "Điểm đi và đến phải khác nhau"
//                    Toast.makeText(context, "Điểm đi và đến phải khác nhau", Toast.LENGTH_SHORT).show()
//                }
//                fromShort.isBlank() -> {
//                    errorMessage = "Mã điểm đi không được để trống"
//                    Toast.makeText(context, "Mã điểm đi không được để trống", Toast.LENGTH_SHORT).show()
//                }
//                toShort.isBlank() -> {
//                    errorMessage = "Mã điểm đến không được để trống"
//                    Toast.makeText(context, "Mã điểm đến không được để trống", Toast.LENGTH_SHORT).show()
//                }
//                date.isBlank() || !isValidDate(date) -> {
//                    errorMessage = "Ngày không hợp lệ (định dạng: dd mmm, yyyy)"
//                    Toast.makeText(context, "Ngày không hợp lệ (định dạng: dd mmm, yyyy)", Toast.LENGTH_SHORT).show()
//                }
//                time.isBlank() || !isValidTime(time) -> {
//                    errorMessage = "Giờ không hợp lệ (định dạng: HH:mm)"
//                    Toast.makeText(context, "Giờ không hợp lệ (định dạng: HH:mm)", Toast.LENGTH_SHORT).show()
//                }
//                arriveTime.isBlank() || !isValidArriveTime(arriveTime) -> {
//                    errorMessage = "Thời gian bay không hợp lệ (định dạng: Xh Ym)"
//                    Toast.makeText(context, "Thời gian bay không hợp lệ (định dạng: Xh Ym)", Toast.LENGTH_SHORT).show()
//                }
//                price.isBlank() || price.toDoubleOrNull()?.let { it <= 0 } ?: true -> {
//                    errorMessage = "Giá vé không hợp lệ"
//                    Toast.makeText(context, "Giá vé không hợp lệ", Toast.LENGTH_SHORT).show()
//                }
//                numberSeat.isBlank() || numberSeat.toIntOrNull()?.let { it <= 0 } ?: true -> {
//                    errorMessage = "Số ghế không hợp lệ"
//                    Toast.makeText(context, "Số ghế không hợp lệ", Toast.LENGTH_SHORT).show()
//                }
//                classSeat !in validClassSeats -> {
//                    errorMessage = "Loại ghế không hợp lệ (Economy class, Business class, First class)"
//                    Toast.makeText(context, "Loại ghế không hợp lệ", Toast.LENGTH_SHORT).show()
//                }
//                typeClass !in validTypeClasses -> {
//                    errorMessage = "Loại vé không hợp lệ (Economy, Business, First)"
//                    Toast.makeText(context, "Loại vé không hợp lệ", Toast.LENGTH_SHORT).show()
//                }
//                else -> {
//                    errorMessage = null
//                    val newFlight = FlightModel(
//                        FlightId = flightId,
//                        AirlineLogo = airlineLogo,
//                        AirlineName = airlineName,
//                        ArriveTime = arriveTime,
//                        ClassSeat = classSeat,
//                        TypeClass = typeClass,
//                        Date = date,
//                        From = from,
//                        FromShort = fromShort,
//                        To = to,
//                        ToShort = toShort,
//                        NumberSeat = numberSeat.toInt(),
//                        Price = price.toDouble(),
//                        ReservedSeats = ""
//                    )
//                    onAddFlight(newFlight)
//                }
//            }
//            validateTrigger = false
//        }
//    }
//
//    Dialog(onDismissRequest = onDismiss) {
//        Surface(
//            shape = MaterialTheme.shapes.medium,
//            color = colorResource(R.color.darkPurple2),
//            modifier = Modifier
//                .fillMaxWidth()
//                .heightIn(max = LocalConfiguration.current.screenHeightDp.dp * 0.9f)
//        ) {
//            Column(
//                modifier = Modifier
//                    .padding(16.dp)
//                    .fillMaxWidth()
//                    .verticalScroll(rememberScrollState()),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = "Thêm Chuyến Bay Mới",
//                    fontSize = 20.sp,
//                    color = Color.White
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                OutlinedTextField(
//                    value = flightId,
//                    onValueChange = { value -> flightId = value },
//                    label = { Text("Mã chuyến bay", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = airlineName,
//                    onValueChange = { value -> airlineName = value },
//                    label = { Text("Tên hãng bay", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = airlineLogo,
//                    onValueChange = { value -> airlineLogo = value },
//                    label = { Text("URL logo hãng bay", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = from,
//                    onValueChange = { value -> from = value },
//                    label = { Text("Điểm đi", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = fromShort,
//                    onValueChange = { value -> fromShort = value },
//                    label = { Text("Mã điểm đi (VD: JFK)", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = to,
//                    onValueChange = { value -> to = value },
//                    label = { Text("Điểm đến", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = toShort,
//                    onValueChange = { value -> toShort = value },
//                    label = { Text("Mã điểm đến (VD: LAX)", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = date,
//                    onValueChange = { value -> date = value },
//                    label = { Text("Ngày (dd mmm, yyyy)", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = time,
//                    onValueChange = { value -> time = value },
//                    label = { Text("Giờ (HH:mm)", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = arriveTime,
//                    onValueChange = { value -> arriveTime = value },
//                    label = { Text("Thời gian bay (Xh Ym)", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = price,
//                    onValueChange = { value -> price = value },
//                    label = { Text("Giá vé", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = numberSeat,
//                    onValueChange = { value -> numberSeat = value },
//                    label = { Text("Số ghế", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = classSeat,
//                    onValueChange = { value -> classSeat = value },
//                    label = { Text("Loại ghế (Economy class, Business class, First class)", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = typeClass,
//                    onValueChange = { value -> typeClass = value },
//                    label = { Text("Loại vé (Economy, Business, First)", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                errorMessage?.let { message ->
//                    Text(
//                        text = message,
//                        color = Color.Red,
//                        fontSize = 14.sp,
//                        modifier = Modifier.padding(bottom = 8.dp)
//                    )
//                }
//                Row {
//                    Button(
//                        onClick = onDismiss,
//                        modifier = Modifier
//                            .weight(1f)
//                            .height(48.dp),
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = Color(0xFFB0BEC5), // Màu xám
//                            contentColor = Color.White
//                        )
//                    ) {
//                        Text("Hủy")
//                    }
//                    Spacer(modifier = Modifier.width(16.dp))
//                    Button(
//                        onClick = { validateTrigger = true },
//                        modifier = Modifier
//                            .weight(1f)
//                            .height(48.dp),
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = Color(0xFF6200EE), // Màu tím
//                            contentColor = Color.White
//                        )
//                    ) {
//                        Text("Thêm")
//                    }
//                }
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun EditFlightDialog(flight: FlightModel, onDismiss: () -> Unit, onUpdateFlight: (FlightModel) -> Unit) {
//    var flightId by remember { mutableStateOf(flight.FlightId) }
//    var airlineName by remember { mutableStateOf(flight.AirlineName) }
//    var airlineLogo by remember { mutableStateOf(flight.AirlineLogo) }
//    var from by remember { mutableStateOf(flight.From) }
//    var to by remember { mutableStateOf(flight.To) }
//    var fromShort by remember { mutableStateOf(flight.FromShort) }
//    var toShort by remember { mutableStateOf(flight.ToShort) }
//    var date by remember { mutableStateOf(flight.Date) }
//    var time by remember { mutableStateOf(flight.Time) }
//    var arriveTime by remember { mutableStateOf(flight.ArriveTime) }
//    var price by remember { mutableStateOf(flight.Price.toString()) }
//    var numberSeat by remember { mutableStateOf(flight.NumberSeat.toString()) }
//    var classSeat by remember { mutableStateOf(flight.ClassSeat) }
//    var typeClass by remember { mutableStateOf(flight.TypeClass) }
//    var errorMessage by remember { mutableStateOf<String?>(null) }
//    var validateTrigger by remember { mutableStateOf(false) }
//
//    val validLocations = listOf(
//        "LosAngles", "NewYork", "San Francisco", "Washington, D.C", "Boston",
//        "Philadelphia", "Chicago", "Las Vegas", "Miami", "Seattle"
//    )
//    val validTypeClasses = listOf("Economy", "Business", "First")
//    val validClassSeats = listOf("Economy class", "Business class", "First class")
//    val context = LocalContext.current
//
//    LaunchedEffect(validateTrigger) {
//        if (validateTrigger) {
//            when {
//                flightId.isBlank() -> {
//                    errorMessage = "Mã chuyến bay không được để trống"
//                    Toast.makeText(context, "Mã chuyến bay không được để trống", Toast.LENGTH_SHORT).show()
//                }
//                airlineName.isBlank() -> {
//                    errorMessage = "Tên hãng bay không được để trống"
//                    Toast.makeText(context, "Tên hãng bay không được để trống", Toast.LENGTH_SHORT).show()
//                }
//                airlineLogo.isBlank() -> {
//                    errorMessage = "Logo hãng bay không được để trống"
//                    Toast.makeText(context, "Logo hãng bay không được để trống", Toast.LENGTH_SHORT).show()
//                }
//                from !in validLocations -> {
//                    errorMessage = "Điểm đi không hợp lệ"
//                    Toast.makeText(context, "Điểm đi không hợp lệ", Toast.LENGTH_SHORT).show()
//                }
//                to !in validLocations -> {
//                    errorMessage = "Điểm đến không hợp lệ"
//                    Toast.makeText(context, "Điểm đến không hợp lệ", Toast.LENGTH_SHORT).show()
//                }
//                from == to -> {
//                    errorMessage = "Điểm đi và đến phải khác nhau"
//                    Toast.makeText(context, "Điểm đi và đến phải khác nhau", Toast.LENGTH_SHORT).show()
//                }
//                fromShort.isBlank() -> {
//                    errorMessage = "Mã điểm đi không được để trống"
//                    Toast.makeText(context, "Mã điểm đi không được để trống", Toast.LENGTH_SHORT).show()
//                }
//                toShort.isBlank() -> {
//                    errorMessage = "Mã điểm đến không được để trống"
//                    Toast.makeText(context, "Mã điểm đến không được để trống", Toast.LENGTH_SHORT).show()
//                }
//                date.isBlank() || !isValidDate(date) -> {
//                    errorMessage = "Ngày không hợp lệ (định dạng: dd mmm, yyyy)"
//                    Toast.makeText(context, "Ngày không hợp lệ (định dạng: dd mmm, yyyy)", Toast.LENGTH_SHORT).show()
//                }
//                time.isBlank() || !isValidTime(time) -> {
//                    errorMessage = "Giờ không hợp lệ (định dạng: HH:mm)"
//                    Toast.makeText(context, "Giờ không hợp lệ (định dạng: HH:mm)", Toast.LENGTH_SHORT).show()
//                }
//                arriveTime.isBlank() || !isValidArriveTime(arriveTime) -> {
//                    errorMessage = "Thời gian bay không hợp lệ (định dạng: Xh Ym)"
//                    Toast.makeText(context, "Thời gian bay không hợp lệ (định dạng: Xh Ym)", Toast.LENGTH_SHORT).show()
//                }
//                price.isBlank() || price.toDoubleOrNull()?.let { it <= 0 } ?: true -> {
//                    errorMessage = "Giá vé không hợp lệ"
//                    Toast.makeText(context, "Giá vé không hợp lệ", Toast.LENGTH_SHORT).show()
//                }
//                numberSeat.isBlank() || numberSeat.toIntOrNull()?.let { it <= 0 } ?: true -> {
//                    errorMessage = "Số ghế không hợp lệ"
//                    Toast.makeText(context, "Số ghế không hợp lệ", Toast.LENGTH_SHORT).show()
//                }
//                classSeat !in validClassSeats -> {
//                    errorMessage = "Loại ghế không hợp lệ (Economy class, Business class, First class)"
//                    Toast.makeText(context, "Loại ghế không hợp lệ", Toast.LENGTH_SHORT).show()
//                }
//                typeClass !in validTypeClasses -> {
//                    errorMessage = "Loại vé không hợp lệ (Economy, Business, First)"
//                    Toast.makeText(context, "Loại vé không hợp lệ", Toast.LENGTH_SHORT).show()
//                }
//                else -> {
//                    errorMessage = null
//                    val updatedFlight = FlightModel(
//                        FlightId = flightId,
//                        AirlineLogo = airlineLogo,
//                        AirlineName = airlineName,
//                        ArriveTime = arriveTime,
//                        ClassSeat = classSeat,
//                        TypeClass = typeClass,
//                        Date = date,
//                        From = from,
//                        FromShort = fromShort,
//                        To = to,
//                        ToShort = toShort,
//                        NumberSeat = numberSeat.toInt(),
//                        Price = price.toDouble(),
//                        ReservedSeats = flight.ReservedSeats,
//                        Passenger = flight.Passenger,
//                        Seats = flight.Seats,
//                        bookingTime = flight.bookingTime
//                    )
//                    onUpdateFlight(updatedFlight)
//                }
//            }
//            validateTrigger = false
//        }
//    }
//
//    Dialog(onDismissRequest = onDismiss) {
//        Surface(
//            shape = MaterialTheme.shapes.medium,
//            color = colorResource(R.color.darkPurple2),
//            modifier = Modifier
//                .fillMaxWidth()
//                .heightIn(max = LocalConfiguration.current.screenHeightDp.dp * 0.9f)
//        ) {
//            Column(
//                modifier = Modifier
//                    .padding(16.dp)
//                    .fillMaxWidth()
//                    .verticalScroll(rememberScrollState()),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = "Chỉnh Sửa Chuyến Bay",
//                    fontSize = 20.sp,
//                    color = Color.White
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                OutlinedTextField(
//                    value = flightId,
//                    onValueChange = { value -> flightId = value },
//                    label = { Text("Mã chuyến bay", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = airlineName,
//                    onValueChange = { value -> airlineName = value },
//                    label = { Text("Tên hãng bay", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = airlineLogo,
//                    onValueChange = { value -> airlineLogo = value },
//                    label = { Text("URL logo hãng bay", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = from,
//                    onValueChange = { value -> from = value },
//                    label = { Text("Điểm đi", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = fromShort,
//                    onValueChange = { value -> fromShort = value },
//                    label = { Text("Mã điểm đi (VD: JFK)", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = to,
//                    onValueChange = { value -> to = value },
//                    label = { Text("Điểm đến", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = toShort,
//                    onValueChange = { value -> toShort = value },
//                    label = { Text("Mã điểm đến (VD: LAX)", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = date,
//                    onValueChange = { value -> date = value },
//                    label = { Text("Ngày (dd mmm, yyyy)", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = time,
//                    onValueChange = { value -> time = value },
//                    label = { Text("Giờ (HH:mm)", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = arriveTime,
//                    onValueChange = { value -> arriveTime = value },
//                    label = { Text("Thời gian bay (Xh Ym)", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = price,
//                    onValueChange = { value -> price = value },
//                    label = { Text("Giá vé", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = numberSeat,
//                    onValueChange = { value -> numberSeat = value },
//                    label = { Text("Số ghế", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = classSeat,
//                    onValueChange = { value -> classSeat = value },
//                    label = { Text("Loại ghế (Economy class, Business class, First class)", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                OutlinedTextField(
//                    value = typeClass,
//                    onValueChange = { value -> typeClass = value },
//                    label = { Text("Loại vé (Economy, Business, First)", color = Color.White) },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        cursorColor = Color.White,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.White,
//                        unfocusedIndicatorColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White
//                    )
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                errorMessage?.let { message ->
//                    Text(
//                        text = message,
//                        color = Color.Red,
//                        fontSize = 14.sp,
//                        modifier = Modifier.padding(bottom = 8.dp)
//                    )
//                }
//                Row {
//                    Button(
//                        onClick = onDismiss,
//                        modifier = Modifier
//                            .weight(1f)
//                            .height(48.dp),
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = Color(0xFFB0BEC5), // Màu xám
//                            contentColor = Color.White
//                        )
//                    ) {
//                        Text("Hủy")
//                    }
//                    Spacer(modifier = Modifier.width(16.dp))
//                    Button(
//                        onClick = { validateTrigger = true },
//                        modifier = Modifier
//                            .weight(1f)
//                            .height(48.dp),
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = Color(0xFF6200EE), // Màu tím
//                            contentColor = Color.White
//                        )
//                    ) {
//                        Text("Cập nhật")
//                    }
//                }
//            }
//        }
//    }
//}
//
//private fun isValidDate(date: String): Boolean {
//    return try {
//        val sdf = SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH)
//        sdf.isLenient = false
//        sdf.parse(date)
//        true
//    } catch (e: Exception) {
//        false
//    }
//}
//
//private fun isValidTime(time: String): Boolean {
//    return try {
//        val sdf = SimpleDateFormat("HH:mm", Locale.ENGLISH)
//        sdf.isLenient = false
//        sdf.parse(time)
//        true
//    } catch (e: Exception) {
//        false
//    }
//}
//
//private fun isValidArriveTime(arriveTime: String): Boolean {
//    return arriveTime.matches(Regex("\\d+h \\d+m"))
//}

package com.example.ticketbookingapp.Activities.Admin

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ticketbookingapp.Domain.FlightModel
import com.example.ticketbookingapp.Domain.UserModel
import com.example.ticketbookingapp.R
import com.example.ticketbookingapp.ViewModel.AdminViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminFlightManagementScreen(
    adminViewModel: AdminViewModel,
    user: UserModel,
    onBackToDashboard: () -> Unit
) {
    var isLoading by remember { mutableStateOf(false) }
    var showAddFlightDialog by remember { mutableStateOf(false) }
    var showEditFlightDialog by remember { mutableStateOf<FlightModel?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val flights by adminViewModel.flights.collectAsState()
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.darkPurple2))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Quản Lý Chuyến Bay",
                fontSize = 32.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Chào, ${user.fullName}",
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier
                    .weight(1f, fill = false)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(flights) { flight ->
                    FlightItem(
                        flight = flight,
                        onEdit = { showEditFlightDialog = flight },
                        onDelete = {
                            coroutineScope.launch {
                                isLoading = true
                                try {
                                    adminViewModel.deleteFlight(flight.FlightId)
                                    Toast.makeText(context, "Xóa chuyến bay thành công", Toast.LENGTH_SHORT).show()
                                } catch (e: Exception) {
                                    Toast.makeText(context, "Lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
                                } finally {
                                    isLoading = false
                                }
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { showAddFlightDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6200EE),
                    contentColor = Color.White
                )
            ) {
                Text("Thêm Chuyến Bay", fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { onBackToDashboard() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF03DAC5),
                    contentColor = Color.White
                )
            ) {
                Text("Quay Lại Dashboard", fontSize = 16.sp)
            }
        }

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        if (showAddFlightDialog) {
            AddFlightDialog(
                onDismiss = { showAddFlightDialog = false },
                onAddFlight = { newFlight ->
                    coroutineScope.launch {
                        isLoading = true
                        try {
                            adminViewModel.addFlight(newFlight)
                            Toast.makeText(context, "Thêm chuyến bay thành công", Toast.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            Toast.makeText(context, "Lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
                        } finally {
                            isLoading = false
                            showAddFlightDialog = false
                        }
                    }
                }
            )
        }

        showEditFlightDialog?.let { flight ->
            EditFlightDialog(
                flight = flight,
                onDismiss = { showEditFlightDialog = null },
                onUpdateFlight = { updatedFlight ->
                    coroutineScope.launch {
                        isLoading = true
                        try {
                            adminViewModel.updateFlight(updatedFlight)
                            Toast.makeText(context, "Cập nhật chuyến bay thành công", Toast.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            Toast.makeText(context, "Lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
                        } finally {
                            isLoading = false
                            showEditFlightDialog = null
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun FlightItem(flight: FlightModel, onEdit: () -> Unit, onDelete: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(colorResource(R.color.darkPurple), shape = MaterialTheme.shapes.medium)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column {
            Text(text = "${flight.AirlineName} (${flight.FlightId})", color = Color.White, fontSize = 16.sp)
            Text(text = "${flight.From} (${flight.FromShort}) -> ${flight.To} (${flight.ToShort})", color = Color.White)
            Text(text = "Ngày: ${flight.Date}", color = Color.White)
            Text(text = "Giờ: ${flight.Time}", color = Color.White)
            Text(text = "Thời gian bay: ${flight.ArriveTime}", color = Color.White)
            Text(text = "Số ghế: ${flight.NumberSeat}", color = Color.White)
            Text(text = "Giá vé: $${String.format("%.2f", flight.Price)}", color = Color.White)
            Text(text = "Loại ghế: ${flight.ClassSeat}", color = Color.White)
            Text(text = "Loại vé: ${flight.TypeClass}", color = Color.White)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onEdit,
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6200EE),
                    contentColor = Color.White
                )
            ) {
                Text("Sửa", fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = onDelete,
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD32F2F),
                    contentColor = Color.White
                )
            ) {
                Text("Xóa", fontSize = 14.sp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFlightDialog(onDismiss: () -> Unit, onAddFlight: (FlightModel) -> Unit) {
    var flightId by remember { mutableStateOf("") }
    var airlineName by remember { mutableStateOf("") }
    var airlineLogo by remember { mutableStateOf("") }
    var from by remember { mutableStateOf("") }
    var to by remember { mutableStateOf("") }
    var fromShort by remember { mutableStateOf("") }
    var toShort by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var arriveTime by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var numberSeat by remember { mutableStateOf("") }
    var classSeat by remember { mutableStateOf("") }
    var typeClass by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var validateTrigger by remember { mutableStateOf(false) }

    val validLocations = listOf(
        "LosAngles", "NewYork", "San Francisco", "Washington, D.C", "Boston",
        "Philadelphia", "Chicago", "Las Vegas", "Miami", "Seattle"
    )
    val validTypeClasses = listOf("Economy", "Business", "First")
    val validClassSeats = listOf("Economy class", "Business class", "First class")
    val context = LocalContext.current

    LaunchedEffect(validateTrigger) {
        if (validateTrigger) {
            when {
                flightId.isBlank() -> {
                    errorMessage = "Mã chuyến bay không được để trống"
                    Toast.makeText(context, "Mã chuyến bay không được để trống", Toast.LENGTH_SHORT).show()
                }
                airlineName.isBlank() -> {
                    errorMessage = "Tên hãng bay không được để trống"
                    Toast.makeText(context, "Tên hãng bay không được để trống", Toast.LENGTH_SHORT).show()
                }
                airlineLogo.isBlank() -> {
                    errorMessage = "Logo hãng bay không được để trống"
                    Toast.makeText(context, "Logo hãng bay không được để trống", Toast.LENGTH_SHORT).show()
                }
                from !in validLocations -> {
                    errorMessage = "Điểm đi không hợp lệ"
                    Toast.makeText(context, "Điểm đi không hợp lệ", Toast.LENGTH_SHORT).show()
                }
                to !in validLocations -> {
                    errorMessage = "Điểm đến không hợp lệ"
                    Toast.makeText(context, "Điểm đến không hợp lệ", Toast.LENGTH_SHORT).show()
                }
                from == to -> {
                    errorMessage = "Điểm đi và đến phải khác nhau"
                    Toast.makeText(context, "Điểm đi và đến phải khác nhau", Toast.LENGTH_SHORT).show()
                }
                fromShort.isBlank() -> {
                    errorMessage = "Mã điểm đi không được để trống"
                    Toast.makeText(context, "Mã điểm đi không được để trống", Toast.LENGTH_SHORT).show()
                }
                toShort.isBlank() -> {
                    errorMessage = "Mã điểm đến không được để trống"
                    Toast.makeText(context, "Mã điểm đến không được để trống", Toast.LENGTH_SHORT).show()
                }
                date.isBlank() || !isValidDate(date) -> {
                    errorMessage = "Ngày không hợp lệ (định dạng: dd mmm, yyyy)"
                    Toast.makeText(context, "Ngày không hợp lệ (định dạng: dd mmm, yyyy)", Toast.LENGTH_SHORT).show()
                }
                time.isBlank() || !isValidTime(time) -> {
                    errorMessage = "Giờ không hợp lệ (định dạng: HH:mm)"
                    Toast.makeText(context, "Giờ không hợp lệ (định dạng: HH:mm)", Toast.LENGTH_SHORT).show()
                }
                arriveTime.isBlank() || !isValidArriveTime(arriveTime) -> {
                    errorMessage = "Thời gian bay không hợp lệ (định dạng: Xh Ym)"
                    Toast.makeText(context, "Thời gian bay không hợp lệ (định dạng: Xh Ym)", Toast.LENGTH_SHORT).show()
                }
                price.isBlank() || price.toDoubleOrNull()?.let { it <= 0 } ?: true -> {
                    errorMessage = "Giá vé không hợp lệ"
                    Toast.makeText(context, "Giá vé không hợp lệ", Toast.LENGTH_SHORT).show()
                }
                numberSeat.isBlank() || numberSeat.toIntOrNull()?.let { it <= 0 } ?: true -> {
                    errorMessage = "Số ghế không hợp lệ"
                    Toast.makeText(context, "Số ghế không hợp lệ", Toast.LENGTH_SHORT).show()
                }
                classSeat !in validClassSeats -> {
                    errorMessage = "Loại ghế không hợp lệ (Economy class, Business class, First class)"
                    Toast.makeText(context, "Loại ghế không hợp lệ", Toast.LENGTH_SHORT).show()
                }
                typeClass !in validTypeClasses -> {
                    errorMessage = "Loại vé không hợp lệ (Economy, Business, First)"
                    Toast.makeText(context, "Loại vé không hợp lệ", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    errorMessage = null
                    val newFlight = FlightModel(
                        FlightId = flightId,
                        AirlineLogo = airlineLogo,
                        AirlineName = airlineName,
                        ArriveTime = arriveTime,
                        ClassSeat = classSeat,
                        TypeClass = typeClass,
                        Date = date,
                        From = from,
                        FromShort = fromShort,
                        To = to,
                        ToShort = toShort,
                        NumberSeat = numberSeat.toInt(),
                        Price = price.toDouble(),
                        ReservedSeats = ""
                    )
                    onAddFlight(newFlight)
                }
            }
            validateTrigger = false
        }
    }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = colorResource(R.color.darkPurple2),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = LocalConfiguration.current.screenHeightDp.dp * 0.9f)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Thêm Chuyến Bay Mới",
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = flightId,
                    onValueChange = { value -> flightId = value },
                    label = { Text("Mã chuyến bay", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = airlineName,
                    onValueChange = { value -> airlineName = value },
                    label = { Text("Tên hãng bay", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = airlineLogo,
                    onValueChange = { value -> airlineLogo = value },
                    label = { Text("URL logo hãng bay", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = from,
                    onValueChange = { value -> from = value },
                    label = { Text("Điểm đi", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = fromShort,
                    onValueChange = { value -> fromShort = value },
                    label = { Text("Mã điểm đi (VD: JFK)", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = to,
                    onValueChange = { value -> to = value },
                    label = { Text("Điểm đến", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = toShort,
                    onValueChange = { value -> toShort = value },
                    label = { Text("Mã điểm đến (VD: LAX)", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = date,
                    onValueChange = { value -> date = value },
                    label = { Text("Ngày (dd mmm, yyyy)", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = time,
                    onValueChange = { value -> time = value },
                    label = { Text("Giờ (HH:mm)", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = arriveTime,
                    onValueChange = { value -> arriveTime = value },
                    label = { Text("Thời gian bay (Xh Ym)", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = price,
                    onValueChange = { value -> price = value },
                    label = { Text("Giá vé", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = numberSeat,
                    onValueChange = { value -> numberSeat = value },
                    label = { Text("Số ghế", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = classSeat,
                    onValueChange = { value -> classSeat = value },
                    label = { Text("Loại ghế (Economy class, Business class, First class)", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = typeClass,
                    onValueChange = { value -> typeClass = value },
                    label = { Text("Loại vé (Economy, Business, First)", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                errorMessage?.let { message ->
                    Text(
                        text = message,
                        color = Color.Red,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                Row {
                    Button(
                        onClick = onDismiss,
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFB0BEC5),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Hủy")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = { validateTrigger = true },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF6200EE),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Thêm")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditFlightDialog(flight: FlightModel, onDismiss: () -> Unit, onUpdateFlight: (FlightModel) -> Unit) {
    var flightId by remember { mutableStateOf(flight.FlightId) }
    var airlineName by remember { mutableStateOf(flight.AirlineName) }
    var airlineLogo by remember { mutableStateOf(flight.AirlineLogo) }
    var from by remember { mutableStateOf(flight.From) }
    var to by remember { mutableStateOf(flight.To) }
    var fromShort by remember { mutableStateOf(flight.FromShort) }
    var toShort by remember { mutableStateOf(flight.ToShort) }
    var date by remember { mutableStateOf(flight.Date) }
    var time by remember { mutableStateOf(flight.Time) }
    var arriveTime by remember { mutableStateOf(flight.ArriveTime) }
    var price by remember { mutableStateOf(flight.Price.toString()) }
    var numberSeat by remember { mutableStateOf(flight.NumberSeat.toString()) }
    var classSeat by remember { mutableStateOf(flight.ClassSeat) }
    var typeClass by remember { mutableStateOf(flight.TypeClass) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var validateTrigger by remember { mutableStateOf(false) }

    val validLocations = listOf(
        "LosAngles", "NewYork", "San Francisco", "Washington, D.C", "Boston",
        "Philadelphia", "Chicago", "Las Vegas", "Miami", "Seattle"
    )
    val validTypeClasses = listOf("Economy", "Business", "First")
    var validClassSeats = listOf("Economy class", "Business class", "First class")
    val context = LocalContext.current

    LaunchedEffect(validateTrigger) {
        if (validateTrigger) {
            when {
                flightId.isBlank() -> {
                    errorMessage = "Mã chuyến bay không được để trống"
                    Toast.makeText(context, "Mã chuyến bay không được để trống", Toast.LENGTH_SHORT).show()
                }
                airlineName.isBlank() -> {
                    errorMessage = "Tên hãng bay không được để trống"
                    Toast.makeText(context, "Tên hãng bay không được để trống", Toast.LENGTH_SHORT).show()
                }
                airlineLogo.isBlank() -> {
                    errorMessage = "Logo hãng bay không được để trống"
                    Toast.makeText(context, "Logo hãng bay không được để trống", Toast.LENGTH_SHORT).show()
                }
                from !in validLocations -> {
                    errorMessage = "Điểm đi không hợp lệ"
                    Toast.makeText(context, "Điểm đi không hợp lệ", Toast.LENGTH_SHORT).show()
                }
                to !in validLocations -> {
                    errorMessage = "Điểm đến không hợp lệ"
                    Toast.makeText(context, "Điểm đến không hợp lệ", Toast.LENGTH_SHORT).show()
                }
                from == to -> {
                    errorMessage = "Điểm đi và đến phải khác nhau"
                    Toast.makeText(context, "Điểm đi và đến phải khác nhau", Toast.LENGTH_SHORT).show()
                }
                fromShort.isBlank() -> {
                    errorMessage = "Mã điểm đi không được để trống"
                    Toast.makeText(context, "Mã điểm đi không được để trống", Toast.LENGTH_SHORT).show()
                }
                toShort.isBlank() -> {
                    errorMessage = "Mã điểm đến không được để trống"
                    Toast.makeText(context, "Mã điểm đến không được để trống", Toast.LENGTH_SHORT).show()
                }
                date.isBlank() || !isValidDate(date) -> {
                    errorMessage = "Ngày không hợp lệ (định dạng: dd mmm, yyyy)"
                    Toast.makeText(context, "Ngày không hợp lệ (định dạng: dd mmm, yyyy)", Toast.LENGTH_SHORT).show()
                }
                time.isBlank() || !isValidTime(time) -> {
                    errorMessage = "Giờ không hợp lệ (định dạng: HH:mm)"
                    Toast.makeText(context, "Giờ không hợp lệ (định dạng: HH:mm)", Toast.LENGTH_SHORT).show()
                }
                arriveTime.isBlank() || !isValidArriveTime(arriveTime) -> {
                    errorMessage = "Thời gian bay không hợp lệ (định dạng: Xh Ym)"
                    Toast.makeText(context, "Thời gian bay không hợp lệ (định dạng: Xh Ym)", Toast.LENGTH_SHORT).show()
                }
                price.isBlank() || price.toDoubleOrNull()?.let { it <= 0 } ?: true -> {
                    errorMessage = "Giá vé không hợp lệ"
                    Toast.makeText(context, "Giá vé không hợp lệ", Toast.LENGTH_SHORT).show()
                }
                numberSeat.isBlank() || numberSeat.toIntOrNull()?.let { it <= 0 } ?: true -> {
                    errorMessage = "Số ghế không hợp lệ"
                    Toast.makeText(context, "Số ghế không hợp lệ", Toast.LENGTH_SHORT).show()
                }
                classSeat !in validClassSeats -> {
                    errorMessage = "Loại ghế không hợp lệ (Economy class, Business class, First class)"
                    Toast.makeText(context, "Loại ghế không hợp lệ", Toast.LENGTH_SHORT).show()
                }
                typeClass !in validTypeClasses -> {
                    errorMessage = "Loại vé không hợp lệ (Economy, Business, First)"
                    Toast.makeText(context, "Loại vé không hợp lệ", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    errorMessage = null
                    val updatedFlight = FlightModel(
                        FlightId = flightId,
                        AirlineLogo = airlineLogo,
                        AirlineName = airlineName,
                        ArriveTime = arriveTime,
                        ClassSeat = classSeat,
                        TypeClass = typeClass,
                        Date = date,
                        From = from,
                        FromShort = fromShort,
                        To = to,
                        ToShort = toShort,
                        NumberSeat = numberSeat.toInt(),
                        Price = price.toDouble(),
                        ReservedSeats = flight.ReservedSeats,
                        Passenger = flight.Passenger,
                        Seats = flight.Seats,
                        bookingTime = flight.bookingTime
                    )
                    onUpdateFlight(updatedFlight)
                }
            }
            validateTrigger = false
        }
    }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = colorResource(R.color.darkPurple2),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = LocalConfiguration.current.screenHeightDp.dp * 0.9f)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Chỉnh Sửa Chuyến Bay",
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = flightId,
                    onValueChange = { value -> flightId = value },
                    label = { Text("Mã chuyến bay", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = airlineName,
                    onValueChange = { value -> airlineName = value },
                    label = { Text("Tên hãng bay", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = airlineLogo,
                    onValueChange = { value -> airlineLogo = value },
                    label = { Text("URL logo hãng bay", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = from,
                    onValueChange = { value -> from = value },
                    label = { Text("Điểm đi", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = fromShort,
                    onValueChange = { value -> fromShort = value },
                    label = { Text("Mã điểm đi (VD: JFK)", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = to,
                    onValueChange = { value -> to = value },
                    label = { Text("Điểm đến", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = toShort,
                    onValueChange = { value -> toShort = value },
                    label = { Text("Mã điểm đến (VD: LAX)", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = date,
                    onValueChange = { value -> date = value },
                    label = { Text("Ngày (dd mmm, yyyy)", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = time,
                    onValueChange = { value -> time = value },
                    label = { Text("Giờ (HH:mm)", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = arriveTime,
                    onValueChange = { value -> arriveTime = value },
                    label = { Text("Thời gian bay (Xh Ym)", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = price,
                    onValueChange = { value -> price = value },
                    label = { Text("Giá vé", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = numberSeat,
                    onValueChange = { value -> numberSeat = value },
                    label = { Text("Số ghế", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = classSeat,
                    onValueChange = { value -> classSeat = value },
                    label = { Text("Loại ghế (Economy class, Business class, First class)", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = typeClass,
                    onValueChange = { value -> typeClass = value },
                    label = { Text("Loại vé (Economy, Business, First)", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.Gray,
                        errorTextColor = Color.Red,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        disabledLabelColor = Color.Gray,
                        errorLabelColor = Color.Red
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                errorMessage?.let { message ->
                    Text(
                        text = message,
                        color = Color.Red,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                Row {
                    Button(
                        onClick = onDismiss,
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFB0BEC5),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Hủy")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = { validateTrigger = true },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF6200EE),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Cập nhật")
                    }
                }
            }
        }
    }
}

private fun isValidDate(date: String): Boolean {
    return try {
        val sdf = SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH)
        sdf.isLenient = false
        sdf.parse(date)
        true
    } catch (e: Exception) {
        false
    }
}

private fun isValidTime(time: String): Boolean {
    return try {
        val sdf = SimpleDateFormat("HH:mm", Locale.ENGLISH)
        sdf.isLenient = false
        sdf.parse(time)
        true
    } catch (e: Exception) {
        false
    }
}

private fun isValidArriveTime(arriveTime: String): Boolean {
    return arriveTime.matches(Regex("\\d+h \\d+m"))
}