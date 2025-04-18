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
//    var username by remember { mutableStateOf("") }
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
//            // Username field
//            OutlinedTextField(
//                value = username,
//                onValueChange = { username = it },
//                label = { Text("Username") },
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
//                    if (username.isEmpty() || password.isEmpty()) {
//                        errorMessage = "Please fill all fields"
//                        return@GradientButton
//                    }
//                    isLoading = true
//                    authViewModel.login(username, password).observeForever { user ->
//                        isLoading = false
//                        if (user != null) {
//                            onLoginSuccess(user)
//                        } else {
//                            errorMessage = "Invalid username or password"
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
import android.content.Intent
import android.widget.Toast
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ticketbookingapp.Activities.Dashboard.DashboardActivity
import com.example.ticketbookingapp.Activities.Splash.GradientButton
import com.example.ticketbookingapp.Domain.UserModel
import com.example.ticketbookingapp.R
import com.example.ticketbookingapp.ViewModel.AuthViewModel

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    onNavigateToRegister: () -> Unit,
    onLoginSuccess: (UserModel) -> Unit
) {
    val context = LocalContext.current
    var identifier by remember { mutableStateOf("") } // Đổi từ username thành identifier
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

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

            // Username or Email field
            OutlinedTextField(
                value = identifier,
                onValueChange = { identifier = it },
                label = { Text("Username or Email") }, // Đổi label
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

            // Password field
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
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

            // Login button
            GradientButton(
                onClick = {
                    if (isLoading) return@GradientButton // Ngăn click khi đang tải
                    if (identifier.isEmpty() || password.isEmpty()) {
                        errorMessage = "Please fill all fields"
                        return@GradientButton
                    }
                    isLoading = true
                    authViewModel.login(identifier, password).observeForever { user ->
                        isLoading = false
                        if (user != null) {
                            onLoginSuccess(user)
                        } else {
                            errorMessage = "Invalid username/email or password"
                        }
                    }
                },
                text = "Login",
                padding = 0
            )

            // Error message
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

            // Navigate to Register
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