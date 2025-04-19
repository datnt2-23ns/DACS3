//package com.example.ticketbookingapp.Activities.Auth
//
//import android.content.Context
//import android.content.Intent
//import android.widget.Toast
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
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.ticketbookingapp.Activities.Dashboard.DashboardActivity
//import com.example.ticketbookingapp.Activities.Splash.GradientButton
//import com.example.ticketbookingapp.Domain.UserModel
//import com.example.ticketbookingapp.R
//import com.example.ticketbookingapp.ViewModel.AuthViewModel
//
//@Composable
//fun LoginScreen(
//    authViewModel: AuthViewModel,
//    onNavigateToRegister: () -> Unit,
//    onLoginSuccess: (UserModel) -> Unit
//) {
//    val context = LocalContext.current
//    var identifier by remember { mutableStateOf("") } // Đổi từ username thành identifier
//    var password by remember { mutableStateOf("") }
//    var isLoading by remember { mutableStateOf(false) }
//    var errorMessage by remember { mutableStateOf<String?>(null) }
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
//            // Username or Email field
//            OutlinedTextField(
//                value = identifier,
//                onValueChange = { identifier = it },
//                label = { Text("Username or Email") }, // Đổi label
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
//            // Password field
//            OutlinedTextField(
//                value = password,
//                onValueChange = { password = it },
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
//            // Login button
//            GradientButton(
//                onClick = {
//                    if (isLoading) return@GradientButton // Ngăn click khi đang tải
//                    if (identifier.isEmpty() || password.isEmpty()) {
//                        errorMessage = "Please fill all fields"
//                        return@GradientButton
//                    }
//                    isLoading = true
//                    authViewModel.login(identifier, password).observeForever { user ->
//                        isLoading = false
//                        if (user != null) {
//                            onLoginSuccess(user)
//                        } else {
//                            errorMessage = "Invalid username/email or password"
//                        }
//                    }
//                },
//                text = "Login",
//                padding = 0
//            )
//
//            // Error message
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
//            // Navigate to Register
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
                text = "Login",
                fontSize = 32.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            OutlinedTextField(
                value = identifier,
                onValueChange = { identifier = it.trim() },
                label = { Text("Username or Email") },
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
                label = { Text("Password") },
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

            Spacer(modifier = Modifier.height(16.dp))

            GradientButton(
                onClick = {
                    if (isLoading) return@GradientButton
                    if (identifier.isEmpty() || password.isEmpty()) {
                        errorMessage = "Please fill all fields"
                        onLoginFailed("Please fill all fields")
                        return@GradientButton
                    }
                    Log.d(TAG, "Attempting login with identifier: $identifier, password length: ${password.length}")
                    isLoading = true
                    try {
                        authViewModel.login(identifier, password).observe(lifecycleOwner) { result ->
                            isLoading = false
                            when (result) {
                                is AuthResult.Success -> {
                                    Log.d(TAG, "Login successful for user: ${result.user.username}")
                                    onLoginSuccess(result.user)
                                }
                                is AuthResult.Failure -> {
                                    Log.d(TAG, "Login failed: ${result.message}")
                                    errorMessage = when {
                                        result.message == "Wrong password" -> "Wrong password. Check uppercase, special characters, or contact support."
                                        result.message.contains("Network error") -> "Network error. Please check your connection."
                                        result.message.contains("Failed to convert") -> "Invalid user data format. Please contact support."
                                        else -> result.message
                                    }
                                    onLoginFailed(errorMessage ?: "Login failed")
                                }
                            }
                        }
                    } catch (e: Exception) {
                        Log.e(TAG, "Error during login: ${e.message}")
                        isLoading = false
                        errorMessage = "Login error: ${e.message}"
                        onLoginFailed("Login error: Please try again")
                    }
                },
                text = "Login",
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
                    text = "If you don't have an account? ",
                    color = Color.White,
                    fontSize = 14.sp
                )
                Text(
                    text = "Register",
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
    }
}

sealed class AuthResult {
    data class Success(val user: UserModel) : AuthResult()
    data class Failure(val message: String) : AuthResult()
}