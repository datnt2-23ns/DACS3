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
//                        AdminUserManagementActivity.newIntent(context, user)
//                    )
//                },
//                text = "Quản Lý Người Dùng",
//                padding = 0
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            GradientButton(
//                onClick = {
////                    context.startActivity(
////                        AdminUserManagementActivity.newIntent(context, user)
////                    )
//                },
//                text = "Thống Kê",
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
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ticketbookingapp.Activities.Auth.LoginActivity
import com.example.ticketbookingapp.Activities.Dashboard.TopBar
import com.example.ticketbookingapp.Domain.UserModel
import com.example.ticketbookingapp.R

@Composable
fun AdminDashboardScreen(user: UserModel) {
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }
    val backgroundColor = colorResource(R.color.darkPurple2)
    val cardColor = colorResource(R.color.darkPurple)
    val gradientColors = listOf(
        colorResource(R.color.purple_500),
        colorResource(R.color.pink)
    )
    val gradientBrush = Brush.horizontalGradient(colors = gradientColors)

    Scaffold(
        topBar = { TopBar(user = user) }
    )
    { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(paddingValues)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColor)
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(top = 26.dp)
                            .align(Alignment.Center),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = cardColor)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                text = "Bảng Điều Khiển Admin",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(20.dp))

                            Text(
                                text = "Chào mừng, ${user.fullName}",
                                fontSize = 18.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(24.dp))

                            CustomGradientButton(
                                onClick = {
                                    if (!isLoading) {
                                        context.startActivity(
                                            AdminFlightManagementActivity.newIntent(context, user)
                                        )
                                    }
                                },
                                text = "Quản Lý Chuyến Bay",
                                gradientBrush = gradientBrush,
                                enabled = !isLoading
                            )

                            CustomGradientButton(
                                onClick = {
                                    if (!isLoading) {
                                        context.startActivity(
                                            AdminBookingManagementActivity.newIntent(context, user)
                                        )
                                    }
                                },
                                text = "Quản Lý Đặt Vé",
                                gradientBrush = gradientBrush,
                                enabled = !isLoading
                            )

                            CustomGradientButton(
                                onClick = {
                                    if (!isLoading) {
                                        context.startActivity(
                                            AdminUserManagementActivity.newIntent(context, user)
                                        )
                                    }
                                },
                                text = "Quản Lý Người Dùng",
                                gradientBrush = gradientBrush,
                                enabled = !isLoading
                            )

                            CustomGradientButton(
                                onClick = {
                                    if (!isLoading) {
                                        context.startActivity(
                                            AdminStatisticsActivity.newIntent(context, user)
                                        )
                                    }
                                },
                                text = "Thống Kê",
                                gradientBrush = gradientBrush,
                                enabled = !isLoading
                            )

                            CustomGradientButton(
                                onClick = {
                                    if (!isLoading) {
                                        context.startActivity(
                                            LoginActivity.newIntent(context).apply {
                                                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                                            }
                                        )
                                        (context as? ComponentActivity)?.finish()
                                    }
                                },
                                text = "Đăng Xuất",
                                gradientBrush = gradientBrush,
                                enabled = !isLoading
                            )
                        }
                    }

                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CustomGradientButton(
    onClick: () -> Unit,
    text: String,
    gradientBrush: Brush,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(gradientBrush, shape = RoundedCornerShape(25.dp)),
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.White,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Gray
        ),
        enabled = enabled
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = if (enabled) Color.White else Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AdminDashboardScreenPreview() {
    val dummyUser = UserModel(
        fullName = "Admin User",
        email = "admin@example.com"
    )
    AdminDashboardScreen(user = dummyUser)
}

object AdminStatisticsActivity {
    fun newIntent(context: android.content.Context, user: UserModel): Intent {
        return Intent(context, AdminStatisticsActivity::class.java).apply {
            putExtra("user", user)
        }
    }
}