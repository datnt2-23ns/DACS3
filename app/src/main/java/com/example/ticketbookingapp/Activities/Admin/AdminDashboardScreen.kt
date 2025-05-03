//package com.example.ticketbookingapp.Activities.Admin
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
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
//import com.example.ticketbookingapp.Activities.Auth.LoginActivity
//import com.example.ticketbookingapp.Activities.Splash.GradientButton
//import com.example.ticketbookingapp.Domain.UserModel
//import com.example.ticketbookingapp.R
//
//@Composable
//fun AdminDashboardScreen(user: UserModel) {
//    val context = LocalContext.current
//    var isLoading by remember { mutableStateOf(false) }
//    val TAG = "AdminDashboardScreen"
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
//            verticalArrangement = Arrangement.Center
//        ) {
//            Text(
//                text = "Bảng Điều Khiển Admin",
//                fontSize = 32.sp,
//                color = Color.White
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            Text(
//                text = "Chào mừng, ${user.fullName}",
//                fontSize = 20.sp,
//                color = Color.White
//            )
//            Spacer(modifier = Modifier.height(32.dp))
//            GradientButton(
//                onClick = {
//                    context.startActivity(
//                        AdminFlightManagementActivity.newIntent(context, user)
//                    )
//                },
//                text = "Quản Lý Chuyến Bay",
//                padding = 0
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            GradientButton(
//                onClick = {
//                    context.startActivity(
//                        AdminBookingManagementActivity.newIntent(context, user)
//                    )
//                },
//                text = "Quản Lý Đặt Vé",
//                padding = 0
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            GradientButton(
//                onClick = {
//                    context.startActivity(
//                        LoginActivity.newIntent(context).apply {
//                            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
//                        }
//                    )
//                    (context as? AppCompatActivity)?.finish()
//                },
//                text = "Đăng Xuất",
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

package com.example.ticketbookingapp.Activities.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ticketbookingapp.Activities.Auth.LoginActivity
import com.example.ticketbookingapp.Activities.Splash.GradientButton
import com.example.ticketbookingapp.Domain.UserModel
import com.example.ticketbookingapp.R

@Composable
fun AdminDashboardScreen(user: UserModel) {
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }
    val TAG = "AdminDashboardScreen"

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
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Bảng Điều Khiển Admin",
                fontSize = 32.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Chào mừng, ${user.fullName}",
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(32.dp))
            GradientButton(
                onClick = {
                    context.startActivity(
                        AdminFlightManagementActivity.newIntent(context, user)
                    )
                },
                text = "Quản Lý Chuyến Bay",
                padding = 0
            )
            Spacer(modifier = Modifier.height(16.dp))
            GradientButton(
                onClick = {
                    context.startActivity(
                        AdminBookingManagementActivity.newIntent(context, user)
                    )
                },
                text = "Quản Lý Đặt Vé",
                padding = 0
            )
            Spacer(modifier = Modifier.height(16.dp))
            GradientButton(
                onClick = {
                    context.startActivity(
                        AdminUserManagementActivity.newIntent(context, user)
                    )
                },
                text = "Quản Lý Người Dùng",
                padding = 0
            )
            Spacer(modifier = Modifier.height(16.dp))
            GradientButton(
                onClick = {
//                    context.startActivity(
//                        AdminUserManagementActivity.newIntent(context, user)
//                    )
                },
                text = "Thống Kê",
                padding = 0
            )
            Spacer(modifier = Modifier.height(16.dp))
            GradientButton(
                onClick = {
                    context.startActivity(
                        LoginActivity.newIntent(context).apply {
                            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        }
                    )
                    (context as? AppCompatActivity)?.finish()
                },
                text = "Đăng Xuất",
                padding = 0
            )
        }

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}