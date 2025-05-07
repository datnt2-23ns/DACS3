//package com.example.ticketbookingapp.Activities.Auth
//
//import android.content.Context
//import android.util.Log
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalLifecycleOwner
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.ticketbookingapp.Activities.Splash.GradientButton
//import com.example.ticketbookingapp.Domain.UserModel
//import com.example.ticketbookingapp.R
//import com.example.ticketbookingapp.ViewModel.AuthViewModel
//
//@Composable
//fun LoginScreen(
//    authViewModel: AuthViewModel,
//    onNavigateToRegister: () -> Unit,
//    onLoginSuccess: (UserModel) -> Unit,
//    onLoginFailed: (String) -> Unit
//) {
//    val context = LocalContext.current
//    var identifier by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var isLoading by remember { mutableStateOf(false) }
//    var errorMessage by remember { mutableStateOf<String?>(null) }
//    val lifecycleOwner = LocalLifecycleOwner.current
//    val TAG = "LoginScreen"
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
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(
//                text = "Login",
//                fontSize = 32.sp,
//                color = Color.White,
//                modifier = Modifier.padding(bottom = 32.dp)
//            )
//
//            OutlinedTextField(
//                value = identifier,
//                onValueChange = { identifier = it.trim() },
//                label = { Text("Username or Email") },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(
//                        color = colorResource(R.color.darkPurple),
//                        shape = RoundedCornerShape(12.dp)
//                    ),
//                colors = OutlinedTextFieldDefaults.colors(
//                    focusedTextColor = Color.White,
//                    unfocusedTextColor = Color.White,
//                    focusedLabelColor = Color.White,
//                    unfocusedLabelColor = Color.White,
//                    focusedBorderColor = Color.White,
//                    unfocusedBorderColor = Color.White
//                )
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            OutlinedTextField(
//                value = password,
//                onValueChange = { password = it.trim() },
//                label = { Text("Password") },
//                visualTransformation = PasswordVisualTransformation(),
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(
//                        color = colorResource(R.color.darkPurple),
//                        shape = RoundedCornerShape(12.dp)
//                    ),
//                colors = OutlinedTextFieldDefaults.colors(
//                    focusedTextColor = Color.White,
//                    unfocusedTextColor = Color.White,
//                    focusedLabelColor = Color.White,
//                    unfocusedLabelColor = Color.White,
//                    focusedBorderColor = Color.White,
//                    unfocusedBorderColor = Color.White
//                )
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            GradientButton(
//                onClick = {
//                    if (isLoading) return@GradientButton
//                    if (identifier.isEmpty() || password.isEmpty()) {
//                        errorMessage = "Please fill all fields"
//                        onLoginFailed("Please fill all fields")
//                        return@GradientButton
//                    }
//                    Log.d(TAG, "Attempting login with identifier: $identifier, password length: ${password.length}")
//                    isLoading = true
//                    try {
//                        authViewModel.login(identifier, password).observe(lifecycleOwner) { result ->
//                            isLoading = false
//                            when (result) {
//                                is AuthResult.Success -> {
//                                    Log.d(TAG, "Login successful for user: ${result.user.username}")
//                                    onLoginSuccess(result.user)
//                                }
//                                is AuthResult.Failure -> {
//                                    Log.d(TAG, "Login failed: ${result.message}")
//                                    errorMessage = when {
//                                        result.message == "Wrong password" -> "Wrong password. Check uppercase, special characters, or contact support."
//                                        result.message.contains("Network error") -> "Network error. Please check your connection."
//                                        result.message.contains("Failed to convert") -> "Invalid user data format. Please contact support."
//                                        else -> result.message
//                                    }
//                                    onLoginFailed(errorMessage ?: "Login failed")
//                                }
//                            }
//                        }
//                    } catch (e: Exception) {
//                        Log.e(TAG, "Error during login: ${e.message}")
//                        isLoading = false
//                        errorMessage = "Login error: ${e.message}"
//                        onLoginFailed("Login error: Please try again")
//                    }
//                },
//                text = "Login",
//                padding = 0
//            )
//
//            errorMessage?.let {
//                Spacer(modifier = Modifier.height(16.dp))
//                Text(
//                    text = it,
//                    color = Color.Red,
//                    fontSize = 14.sp,
//                    textAlign = TextAlign.Center
//                )
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.Center
//            ) {
//                Text(
//                    text = "If you don't have an account? ",
//                    color = Color.White,
//                    fontSize = 14.sp
//                )
//                Text(
//                    text = "Register",
//                    color = colorResource(R.color.orange),
//                    fontSize = 14.sp,
//                    modifier = Modifier.clickable { onNavigateToRegister() }
//                )
//            }
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
//sealed class AuthResult {
//    data class Success(val user: UserModel) : AuthResult()
//    data class Failure(val message: String) : AuthResult()
//}

package com.example.ticketbookingapp.Activities.Auth

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ticketbookingapp.Activities.Splash.GradientButton
import com.example.ticketbookingapp.Domain.UserModel
import com.example.ticketbookingapp.R
import com.example.ticketbookingapp.ViewModel.AuthViewModel

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    onNavigateToRegister: () -> Unit,
    onLoginSuccess: (UserModel) -> Unit,
    onLoginFailed: (String) -> Unit
) {
    val context = LocalContext.current
    var identifier by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var showForgotPasswordDialog by remember { mutableStateOf(false) }
    var resetEmail by remember { mutableStateOf("") }
    var resetMessage by remember { mutableStateOf<String?>(null) }
    val lifecycleOwner = LocalLifecycleOwner.current
    val TAG = "LoginScreen"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.darkPurple2))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Đăng nhập",
                fontSize = 32.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            OutlinedTextField(
                value = identifier,
                onValueChange = { identifier = it.trim() },
                label = { Text("Tên người dùng hoặc Email") },
                placeholder = { Text("Nhập tên người dùng hoặc email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(R.color.darkPurple),
                        shape = RoundedCornerShape(12.dp)
                    ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it.trim() },
                label = { Text("Mật khẩu") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(R.color.darkPurple),
                        shape = RoundedCornerShape(12.dp)
                    ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Quên mật khẩu?",
                color = colorResource(R.color.orange),
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable { showForgotPasswordDialog = true }
            )

            Spacer(modifier = Modifier.height(16.dp))

            GradientButton(
                onClick = {
                    if (isLoading) return@GradientButton
                    if (identifier.isEmpty() || password.isEmpty()) {
                        errorMessage = "Vui lòng điền đầy đủ các trường"
                        onLoginFailed("Vui lòng điền đầy đủ các trường")
                        return@GradientButton
                    }
                    Log.d(TAG, "Đang cố gắng đăng nhập với identifier: $identifier, độ dài mật khẩu: ${password.length}")
                    isLoading = true
                    try {
                        authViewModel.login(identifier, password).observe(lifecycleOwner) { result ->
                            isLoading = false
                            when (result) {
                                is AuthResult.Success -> {
                                    Log.d(TAG, "Đăng nhập thành công cho người dùng: ${result.user.username}")
                                    onLoginSuccess(result.user)
                                }
                                is AuthResult.Failure -> {
                                    Log.d(TAG, "Đăng nhập thất bại: ${result.message}")
                                    errorMessage = when {
                                        result.message == "Sai mật khẩu" -> "Mật khẩu không đúng. Vui lòng thử lại hoặc đặt lại mật khẩu."
                                        result.message == "Tên người dùng hoặc email không tồn tại" -> "Tên người dùng hoặc email không tồn tại."
                                        result.message.contains("Network error") -> "Lỗi mạng. Vui lòng kiểm tra kết nối của bạn."
                                        result.message.contains("Email không được đăng ký trong Firebase Authentication") -> "Email không được đăng ký. Vui lòng kiểm tra lại."
                                        else -> result.message
                                    }
                                    onLoginFailed(errorMessage ?: "Đăng nhập thất bại")
                                }
                            }
                        }
                    } catch (e: Exception) {
                        Log.e(TAG, "Lỗi trong quá trình đăng nhập: ${e.message}")
                        isLoading = false
                        errorMessage = "Lỗi đăng nhập: ${e.message}"
                        onLoginFailed("Lỗi đăng nhập: Vui lòng thử lại")
                    }
                },
                text = "Đăng nhập",
                padding = 0
            )

            errorMessage?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = it,
                    color = Color.Red,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Nếu bạn chưa có tài khoản? ",
                    color = Color.White,
                    fontSize = 14.sp
                )
                Text(
                    text = "Đăng ký",
                    color = colorResource(R.color.orange),
                    fontSize = 14.sp,
                    modifier = Modifier.clickable { onNavigateToRegister() }
                )
            }
        }

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Hộp thoại Quên mật khẩu
        if (showForgotPasswordDialog) {
            Dialog(onDismissRequest = { showForgotPasswordDialog = false }) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Đặt lại mật khẩu",
                            fontSize = 20.sp,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = resetEmail,
                            onValueChange = { resetEmail = it.trim() },
                            label = { Text("Email") },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                        )

                        resetMessage?.let {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = it,
                                color = if (it.contains("Lỗi") || it.contains("không hỗ trợ")) Color.Red else Color.Green,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            TextButton(onClick = { showForgotPasswordDialog = false }) {
                                Text("Hủy")
                            }
                            GradientButton(
                                onClick = {
                                    if (resetEmail.isEmpty()) {
                                        resetMessage = "Vui lòng nhập email"
                                        return@GradientButton
                                    }
                                    authViewModel.sendPasswordResetEmail(resetEmail)
                                        .observe(lifecycleOwner) { message ->
                                            resetMessage = message
                                        }
                                },
                                text = "Gửi",
                                padding = 8
                            )
                        }
                    }
                }
            }
        }
    }
}