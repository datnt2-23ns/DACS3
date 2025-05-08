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
//import com.example.ticketbookingapp.Domain.UserModel
//import com.example.ticketbookingapp.R
//import com.example.ticketbookingapp.ViewModel.AdminViewModel
//import kotlinx.coroutines.launch
//
//@Composable
//fun AdminUserManagementScreen(
//    adminViewModel: AdminViewModel,
//    user: UserModel,
//    onBackToDashboard: () -> Unit
//) {
//    var isLoading by remember { mutableStateOf(false) }
//    val coroutineScope = rememberCoroutineScope()
//    val users by adminViewModel.users.collectAsState()
//    val context = LocalContext.current
//    val TAG = "AdminUserManagementScreen"
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
//                text = "Quản Lý Người Dùng",
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
//            users.forEach { userItem ->
//                UserItem(
//                    user = userItem,
//                    onDelete = {
//                        coroutineScope.launch {
//                            isLoading = true
//                            try {
//                                adminViewModel.deleteUser(userItem.username)
//                                Toast.makeText(context, "Xóa người dùng thành công", Toast.LENGTH_SHORT).show()
//                            } catch (e: Exception) {
//                                Toast.makeText(context, "Lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
//                            } finally {
//                                isLoading = false
//                            }
//                        }
//                    }
//                )
//            }
//
//            Spacer(modifier = Modifier.height(32.dp))
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
//fun UserItem(user: UserModel, onDelete: () -> Unit) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//            .background(colorResource(R.color.darkPurple), shape = MaterialTheme.shapes.medium),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(text = "Họ tên: ${user.fullName}", color = Color.White)
//            Text(text = "Email: ${user.email}", color = Color.White)
//            Text(text = "Số điện thoại: ${user.phoneNumber}", color = Color.White)
//            Text(text = "Ngày sinh: ${user.dateOfBirth}", color = Color.White)
//            Text(text = "Giới tính: ${user.gender}", color = Color.White)
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
//import com.example.ticketbookingapp.Domain.UserModel
//import com.example.ticketbookingapp.R
//import com.example.ticketbookingapp.ViewModel.AdminViewModel
//import kotlinx.coroutines.launch
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AdminUserManagementScreen(
//    adminViewModel: AdminViewModel,
//    user: UserModel,
//    onBackToDashboard: () -> Unit
//) {
//    var isLoading by remember { mutableStateOf(false) }
//    val coroutineScope = rememberCoroutineScope()
//    val users by adminViewModel.users.collectAsState()
//    val context = LocalContext.current
//    val TAG = "AdminUserManagementScreen"
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
//                text = "Quản Lý Người Dùng",
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
//            LazyColumn(
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxWidth(),
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                items(users) { userItem ->
//                    UserItem(
//                        user = userItem,
//                        onDelete = {
//                            coroutineScope.launch {
//                                isLoading = true
//                                try {
//                                    adminViewModel.deleteUser(userItem.username)
//                                    Toast.makeText(context, "Xóa người dùng thành công", Toast.LENGTH_SHORT).show()
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
//            Spacer(modifier = Modifier.height(32.dp))
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
//fun UserItem(user: UserModel, onDelete: () -> Unit) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//            .background(colorResource(R.color.darkPurple), shape = MaterialTheme.shapes.medium),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(text = "Họ tên: ${user.fullName}", color = Color.White)
//            Text(text = "Email: ${user.email}", color = Color.White)
//            Text(text = "Số điện thoại: ${user.phoneNumber}", color = Color.White)
//            Text(text = "Ngày sinh: ${user.dateOfBirth}", color = Color.White)
//            Text(text = "Giới tính: ${user.gender}", color = Color.White)
//        }
//        Button(
//            onClick = onDelete,
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Color(0xFFD32F2F), // Màu đỏ cho nút Xóa
//                contentColor = Color.White
//            )
//        ) {
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
//import com.example.ticketbookingapp.Domain.UserModel
//import com.example.ticketbookingapp.R
//import com.example.ticketbookingapp.ViewModel.AdminViewModel
//import kotlinx.coroutines.launch
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AdminUserManagementScreen(
//    adminViewModel: AdminViewModel,
//    user: UserModel,
//    onBackToDashboard: () -> Unit
//) {
//    var isLoading by remember { mutableStateOf(false) }
//    val coroutineScope = rememberCoroutineScope()
//    val users by adminViewModel.users.collectAsState()
//    val context = LocalContext.current
//    val TAG = "AdminUserManagementScreen"
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
//                text = "Quản Lý Người Dùng",
//                fontSize = 32.sp,
//                color = Color.White
//            )
//            Spacer(modifier = Modifier.height(32.dp))
//
//            LazyColumn(
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxWidth(),
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                items(users) { userItem ->
//                    UserItem(
//                        user = userItem,
//                        onDelete = {
//                            coroutineScope.launch {
//                                isLoading = true
//                                try {
//                                    adminViewModel.deleteUser(userItem.username)
//                                    Toast.makeText(context, "Xóa người dùng thành công", Toast.LENGTH_SHORT).show()
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
//            Spacer(modifier = Modifier.height(32.dp))
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
//fun UserItem(user: UserModel, onDelete: () -> Unit) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//            .background(colorResource(R.color.darkPurple), shape = MaterialTheme.shapes.medium),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(text = "Họ tên: ${user.fullName}", color = Color.White)
//            Text(text = "Email: ${user.email}", color = Color.White)
//            Text(text = "Số điện thoại: ${user.phoneNumber}", color = Color.White)
//            Text(text = "Ngày sinh: ${user.dateOfBirth}", color = Color.White)
//            Text(text = "Giới tính: ${user.gender}", color = Color.White)
//        }
//        Button(
//            onClick = onDelete,
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Color(0xFFD32F2F), // Màu đỏ cho nút Xóa
//                contentColor = Color.White
//            )
//        ) {
//            Text("Xóa")
//        }
//    }
//}

package com.example.ticketbookingapp.Activities.Admin

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ticketbookingapp.Activities.Splash.GradientButton
import com.example.ticketbookingapp.Domain.UserModel
import com.example.ticketbookingapp.R
import com.example.ticketbookingapp.ViewModel.AdminViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminUserManagementScreen(
    adminViewModel: AdminViewModel,
    user: UserModel,
    onBackToDashboard: () -> Unit
) {
    var isLoading by remember { mutableStateOf(false) }
    var showDeleteConfirmDialog by remember { mutableStateOf<UserModel?>(null) } // State cho dialog xác nhận xóa
    val coroutineScope = rememberCoroutineScope()
    val users by adminViewModel.users.collectAsState()
    val context = LocalContext.current
    val TAG = "AdminUserManagementScreen"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.darkPurple2))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Quản Lý Người Dùng",
                fontSize = 32.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(32.dp))

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(users) { userItem ->
                    UserItem(
                        user = userItem,
                        onDelete = { showDeleteConfirmDialog = userItem } // Hiển thị dialog xác nhận
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
            GradientButton(
                onClick = { onBackToDashboard() },
                text = "Quay Lại Dashboard",
                padding = 0
            )
        }

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        showDeleteConfirmDialog?.let { userToDelete ->
            DeleteConfirmDialog(
                user = userToDelete,
                onDismiss = { showDeleteConfirmDialog = null },
                onConfirm = {
                    coroutineScope.launch {
                        isLoading = true
                        try {
                            adminViewModel.deleteUser(userToDelete.username)
                            Toast.makeText(context, "Xóa người dùng thành công", Toast.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            Toast.makeText(context, "Lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
                        } finally {
                            isLoading = false
                            showDeleteConfirmDialog = null
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun UserItem(user: UserModel, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(colorResource(R.color.darkPurple), shape = MaterialTheme.shapes.medium),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Họ tên: ${user.fullName}", color = Color.White)
            Text(text = "Email: ${user.email}", color = Color.White)
            Text(text = "Số điện thoại: ${user.phoneNumber}", color = Color.White)
            Text(text = "Ngày sinh: ${user.dateOfBirth}", color = Color.White)
            Text(text = "Giới tính: ${user.gender}", color = Color.White)
        }
        Button(
            onClick = onDelete,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD32F2F), // Màu đỏ cho nút Xóa
                contentColor = Color.White
            )
        ) {
            Text("Xóa")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteConfirmDialog(
    user: UserModel,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = colorResource(R.color.darkPurple2),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Xác Nhận Xóa Người Dùng",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Bạn có chắc chắn muốn xóa người dùng ${user.fullName} (${user.email})?",
                    color = Color.White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
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
                        onClick = onConfirm,
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFD32F2F),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Xóa")
                    }
                }
            }
        }
    }
}