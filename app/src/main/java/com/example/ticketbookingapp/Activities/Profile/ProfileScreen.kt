//package com.example.ticketbookingapp.Activities.Profile
//
//import android.widget.Toast
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.constraintlayout.compose.ConstraintLayout
//import com.example.ticketbookingapp.Activities.Dashboard.MyBottomBar
//import com.example.ticketbookingapp.Activities.Dashboard.YellowTitle
//import com.example.ticketbookingapp.Activities.Splash.GradientButton
//import com.example.ticketbookingapp.Domain.UserModel
//import com.example.ticketbookingapp.R
//import com.example.ticketbookingapp.ViewModel.AuthViewModel
//import com.google.firebase.database.FirebaseDatabase
//
//@Composable
//fun ProfileScreen(
//    user: UserModel,
//    authViewModel: AuthViewModel,
//    onUpdateSuccess: () -> Unit,
//    onUpdateFailed: (String) -> Unit
//) {
//    val context = LocalContext.current
//    var fullName by remember { mutableStateOf(user.fullName) }
//    var email by remember { mutableStateOf(user.email) }
//    var dateOfBirth by remember { mutableStateOf(user.dateOfBirth) }
//    var gender by remember { mutableStateOf(user.gender) }
//    var phoneNumber by remember { mutableStateOf(user.phoneNumber) }
//    var isEditing by remember { mutableStateOf(false) }
//    var newPassword by remember { mutableStateOf("") }
//    var confirmPassword by remember { mutableStateOf("") }
//    var isChangingPassword by remember { mutableStateOf(false) }
//
//    Scaffold(
//        bottomBar = { MyBottomBar(user = user, currentScreen = "Profile") },
//        modifier = Modifier.fillMaxSize()
//    ) { paddingValues ->
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(colorResource(R.color.darkPurple2))
//                .padding(paddingValues)
//                .padding(horizontal = 32.dp), // Match Dashboard padding
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            item {
//                ProfileTopBar(user = user)
//            }
//
//            item {
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(
//                            colorResource(R.color.darkPurple),
//                            shape = RoundedCornerShape(20.dp)
//                        )
//                        .padding(vertical = 16.dp, horizontal = 24.dp)
//                ) {
//                    YellowTitle("Personal Information")
//
//                    // Full Name
//                    OutlinedTextField(
//                        value = fullName,
//                        onValueChange = { if (isEditing) fullName = it },
//                        label = { Text("Full Name") },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(
//                                color = colorResource(R.color.lightPurple),
//                                shape = RoundedCornerShape(8.dp)
//                            ),
//                        enabled = isEditing,
//                        colors = OutlinedTextFieldDefaults.colors(
//                            focusedTextColor = Color.White,
//                            unfocusedTextColor = Color.White,
//                            focusedLabelColor = Color.White,
//                            unfocusedLabelColor = Color.White,
//                            focusedBorderColor = Color.White,
//                            unfocusedBorderColor = Color.White,
//                            disabledTextColor = Color.White,
//                            disabledLabelColor = Color.White,
//                            disabledBorderColor = Color.White
//                        )
//                    )
//
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    // Email
//                    OutlinedTextField(
//                        value = email,
//                        onValueChange = { if (isEditing) email = it },
//                        label = { Text("Email") },
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(
//                                color = colorResource(R.color.lightPurple),
//                                shape = RoundedCornerShape(8.dp)
//                            ),
//                        enabled = isEditing,
//                        colors = OutlinedTextFieldDefaults.colors(
//                            focusedTextColor = Color.White,
//                            unfocusedTextColor = Color.White,
//                            focusedLabelColor = Color.White,
//                            unfocusedLabelColor = Color.White,
//                            focusedBorderColor = Color.White,
//                            unfocusedBorderColor = Color.White,
//                            disabledTextColor = Color.White,
//                            disabledLabelColor = Color.White,
//                            disabledBorderColor = Color.White
//                        )
//                    )
//
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    // Date of Birth
//                    OutlinedTextField(
//                        value = dateOfBirth,
//                        onValueChange = { if (isEditing) dateOfBirth = it },
//                        label = { Text("Date of Birth (dd/mm/yyyy)") },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(
//                                color = colorResource(R.color.lightPurple),
//                                shape = RoundedCornerShape(8.dp)
//                            ),
//                        enabled = isEditing,
//                        colors = OutlinedTextFieldDefaults.colors(
//                            focusedTextColor = Color.White,
//                            unfocusedTextColor = Color.White,
//                            focusedLabelColor = Color.White,
//                            unfocusedLabelColor = Color.White,
//                            focusedBorderColor = Color.White,
//                            unfocusedBorderColor = Color.White,
//                            disabledTextColor = Color.White,
//                            disabledLabelColor = Color.White,
//                            disabledBorderColor = Color.White
//                        )
//                    )
//
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    // Gender
//                    var expanded by remember { mutableStateOf(false) }
//                    val genderOptions = listOf("Male", "Female", "Other")
//                    Box {
//                        OutlinedTextField(
//                            value = gender,
//                            onValueChange = {},
//                            label = { Text("Gender") },
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .background(
//                                    color = colorResource(R.color.lightPurple),
//                                    shape = RoundedCornerShape(8.dp)
//                                ),
//                            enabled = false,
//                            colors = OutlinedTextFieldDefaults.colors(
//                                focusedTextColor = Color.White,
//                                unfocusedTextColor = Color.White,
//                                focusedLabelColor = Color.White,
//                                unfocusedLabelColor = Color.White,
//                                focusedBorderColor = Color.White,
//                                unfocusedBorderColor = Color.White,
//                                disabledTextColor = Color.White,
//                                disabledLabelColor = Color.White,
//                                disabledBorderColor = Color.White
//                            )
//                        )
//                        if (isEditing) {
//                            DropdownMenu(
//                                expanded = expanded,
//                                onDismissRequest = { expanded = false }
//                            ) {
//                                genderOptions.forEach { option ->
//                                    DropdownMenuItem(
//                                        text = { Text(option) },
//                                        onClick = {
//                                            gender = option
//                                            expanded = false
//                                        }
//                                    )
//                                }
//                            }
//                            Spacer(
//                                modifier = Modifier
//                                    .matchParentSize()
//                                    .clickable { expanded = true }
//                            )
//                        }
//                    }
//
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    // Phone Number
//                    OutlinedTextField(
//                        value = phoneNumber,
//                        onValueChange = { if (isEditing) phoneNumber = it },
//                        label = { Text("Phone Number") },
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(
//                                color = colorResource(R.color.lightPurple),
//                                shape = RoundedCornerShape(8.dp)
//                            ),
//                        enabled = isEditing,
//                        colors = OutlinedTextFieldDefaults.colors(
//                            focusedTextColor = Color.White,
//                            unfocusedTextColor = Color.White,
//                            focusedLabelColor = Color.White,
//                            unfocusedLabelColor = Color.White,
//                            focusedBorderColor = Color.White,
//                            unfocusedBorderColor = Color.White,
//                            disabledTextColor = Color.White,
//                            disabledLabelColor = Color.White,
//                            disabledBorderColor = Color.White
//                        )
//                    )
//
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    // Update Button
//                    GradientButton(
//                        onClick = {
//                            if (!isEditing) {
//                                isEditing = true
//                            } else {
//                                // Validate inputs
//                                if (fullName.isEmpty()) {
//                                    onUpdateFailed("Full Name is required")
//                                    return@GradientButton
//                                }
//                                val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
//                                if (!email.matches(emailRegex)) {
//                                    onUpdateFailed("Invalid email format")
//                                    return@GradientButton
//                                }
//                                if (dateOfBirth.isEmpty()) {
//                                    onUpdateFailed("Date of Birth is required")
//                                    return@GradientButton
//                                }
//                                if (gender.isEmpty()) {
//                                    onUpdateFailed("Gender is required")
//                                    return@GradientButton
//                                }
//                                val phoneRegex = Regex("^0[0-9]{9}$")
//                                if (!phoneNumber.matches(phoneRegex)) {
//                                    onUpdateFailed("Phone Number must be 10 digits and start with 0")
//                                    return@GradientButton
//                                }
//
//                                // Update user info in Firebase
//                                val updatedUser = UserModel(
//                                    username = user.username,
//                                    password = user.password,
//                                    role = user.role,
//                                    email = email,
//                                    fullName = fullName,
//                                    dateOfBirth = dateOfBirth,
//                                    gender = gender,
//                                    phoneNumber = phoneNumber
//                                )
//                                FirebaseDatabase.getInstance().getReference("Users")
//                                    .child(user.username)
//                                    .setValue(updatedUser)
//                                    .addOnSuccessListener {
//                                        isEditing = false
//                                        onUpdateSuccess()
//                                    }
//                                    .addOnFailureListener {
//                                        onUpdateFailed("Failed to update profile")
//                                    }
//                            }
//                        },
//                        text = if (isEditing) "Save" else "Update Profile"
//                    )
//                }
//            }
//
//            item {
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(
//                            colorResource(R.color.darkPurple),
//                            shape = RoundedCornerShape(20.dp)
//                        )
//                        .padding(vertical = 16.dp, horizontal = 24.dp)
//                ) {
//                    YellowTitle("Account Information")
//
//                    // Username
//                    OutlinedTextField(
//                        value = user.username,
//                        onValueChange = {},
//                        label = { Text("Username") },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(
//                                color = colorResource(R.color.lightPurple),
//                                shape = RoundedCornerShape(8.dp)
//                            ),
//                        enabled = false,
//                        colors = OutlinedTextFieldDefaults.colors(
//                            focusedTextColor = Color.White,
//                            unfocusedTextColor = Color.White,
//                            focusedLabelColor = Color.White,
//                            unfocusedLabelColor = Color.White,
//                            focusedBorderColor = Color.White,
//                            unfocusedBorderColor = Color.White,
//                            disabledTextColor = Color.White,
//                            disabledLabelColor = Color.White,
//                            disabledBorderColor = Color.White
//                        )
//                    )
//
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    // Password
//                    OutlinedTextField(
//                        value = "******",
//                        onValueChange = {},
//                        label = { Text("Password") },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(
//                                color = colorResource(R.color.lightPurple),
//                                shape = RoundedCornerShape(8.dp)
//                            ),
//                        enabled = false,
//                        colors = OutlinedTextFieldDefaults.colors(
//                            focusedTextColor = Color.White,
//                            unfocusedTextColor = Color.White,
//                            focusedLabelColor = Color.White,
//                            unfocusedLabelColor = Color.White,
//                            focusedBorderColor = Color.White,
//                            unfocusedBorderColor = Color.White,
//                            disabledTextColor = Color.White,
//                            disabledLabelColor = Color.White,
//                            disabledBorderColor = Color.White
//                        )
//                    )
//
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    // Change Password Button
//                    GradientButton(
//                        onClick = { isChangingPassword = true },
//                        text = "Change Password"
//                    )
//                }
//            }
//
//            // Change Password Dialog
//            if (isChangingPassword) {
//                item {
//                    AlertDialog(
//                        onDismissRequest = { isChangingPassword = false },
//                        title = { Text("Change Password") },
//                        text = {
//                            Column(
//                                modifier = Modifier
//                                    .background(
//                                        color = colorResource(R.color.darkPurple),
//                                        shape = RoundedCornerShape(12.dp)
//                                    )
//                                    .padding(16.dp)
//                            ) {
//                                OutlinedTextField(
//                                    value = newPassword,
//                                    onValueChange = { newPassword = it },
//                                    label = { Text("New Password") },
//                                    visualTransformation = PasswordVisualTransformation(),
//                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .background(
//                                            color = colorResource(R.color.lightPurple),
//                                            shape = RoundedCornerShape(8.dp)
//                                        ),
//                                    colors = OutlinedTextFieldDefaults.colors(
//                                        focusedTextColor = Color.White,
//                                        unfocusedTextColor = Color.White,
//                                        focusedLabelColor = Color.White,
//                                        unfocusedLabelColor = Color.White,
//                                        focusedBorderColor = Color.White,
//                                        unfocusedBorderColor = Color.White
//                                    )
//                                )
//                                Spacer(modifier = Modifier.height(16.dp))
//                                OutlinedTextField(
//                                    value = confirmPassword,
//                                    onValueChange = { confirmPassword = it },
//                                    label = { Text("Confirm Password") },
//                                    visualTransformation = PasswordVisualTransformation(),
//                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .background(
//                                            color = colorResource(R.color.lightPurple),
//                                            shape = RoundedCornerShape(8.dp)
//                                        ),
//                                    colors = OutlinedTextFieldDefaults.colors(
//                                        focusedTextColor = Color.White,
//                                        unfocusedTextColor = Color.White,
//                                        focusedLabelColor = Color.White,
//                                        unfocusedLabelColor = Color.White,
//                                        focusedBorderColor = Color.White,
//                                        unfocusedBorderColor = Color.White
//                                    )
//                                )
//                            }
//                        },
//                        confirmButton = {
//                            GradientButton(
//                                onClick = {
//                                    val passwordRegex = Regex("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{6,}$")
//                                    if (!newPassword.matches(passwordRegex)) {
//                                        onUpdateFailed("Password must contain an uppercase letter, a number, and a special character")
//                                        return@GradientButton
//                                    }
//                                    if (newPassword != confirmPassword) {
//                                        onUpdateFailed("Passwords do not match")
//                                        return@GradientButton
//                                    }
//
//                                    // Update password in Firebase
//                                    val updatedUser = UserModel(
//                                        username = user.username,
//                                        password = newPassword,
//                                        role = user.role,
//                                        email = user.email,
//                                        fullName = user.fullName,
//                                        dateOfBirth = user.dateOfBirth,
//                                        gender = user.gender,
//                                        phoneNumber = user.phoneNumber
//                                    )
//                                    FirebaseDatabase.getInstance().getReference("Users")
//                                        .child(user.username)
//                                        .setValue(updatedUser)
//                                        .addOnSuccessListener {
//                                            isChangingPassword = false
//                                            newPassword = ""
//                                            confirmPassword = ""
//                                            onUpdateSuccess()
//                                        }
//                                        .addOnFailureListener {
//                                            onUpdateFailed("Failed to change password")
//                                        }
//                                },
//                                text = "Save"
//                            )
//                        },
//                        dismissButton = {
//                            TextButton(onClick = { isChangingPassword = false }) {
//                                Text("Cancel")
//                            }
//                        },
//                        containerColor = colorResource(R.color.darkPurple2)
//                    )
//                }
//            }
//
//            // Spacer cuối để đảm bảo cuộn
//            item {
//                Spacer(modifier = Modifier.height(16.dp))
//            }
//        }
//    }
//}
//
//@Composable
//fun ProfileTopBar(user: UserModel) {
//    ConstraintLayout(
//        modifier = Modifier
//            .padding(horizontal = 32.dp)
//            .fillMaxWidth()
//            .wrapContentHeight()
//    ) {
//        val (world, name, profile, notification, title) = createRefs()
//        Image(
//            painter = painterResource(R.drawable.world),
//            contentDescription = null,
//            modifier = Modifier
//                .clickable { }
//                .constrainAs(world) {
//                    top.linkTo(parent.top)
//                    bottom.linkTo(parent.bottom)
//                    start.linkTo(parent.start)
//                }
//        )
//
//        Image(
//            painter = painterResource(R.drawable.profile),
//            contentDescription = null,
//            modifier = Modifier
//                .constrainAs(profile) {
//                    top.linkTo(parent.top)
//                    bottom.linkTo(parent.bottom)
//                    start.linkTo(parent.start)
//                }
//        )
//
//        Image(
//            painter = painterResource(R.drawable.bell_icon),
//            contentDescription = null,
//            modifier = Modifier
//                .constrainAs(notification) {
//                    top.linkTo(parent.top)
//                    bottom.linkTo(parent.bottom)
//                    end.linkTo(parent.end)
//                }
//        )
//
//        Text(
//            text = user.fullName,
//            color = Color.White,
//            fontWeight = FontWeight.Bold,
//            fontSize = 14.sp,
//            modifier = Modifier.constrainAs(name) {
//                top.linkTo(parent.top)
//                start.linkTo(profile.end)
//                bottom.linkTo(parent.bottom)
//            }
//        )
//
//        Text(
//            text = "Profile",
//            color = Color.White,
//            fontWeight = FontWeight.Bold,
//            fontSize = 25.sp,
//            modifier = Modifier
//                .constrainAs(title) {
//                    top.linkTo(profile.bottom)
//                    bottom.linkTo(parent.bottom)
//                }
//        )
//    }
//}

package com.example.ticketbookingapp.Activities.Profile

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ticketbookingapp.Activities.Dashboard.MyBottomBar
import com.example.ticketbookingapp.Activities.Dashboard.YellowTitle
import com.example.ticketbookingapp.Activities.Splash.GradientButton
import com.example.ticketbookingapp.Activities.Splash.SplashActivity
import com.example.ticketbookingapp.Domain.UserModel
import com.example.ticketbookingapp.R
import com.example.ticketbookingapp.ViewModel.AuthViewModel
import com.google.firebase.database.FirebaseDatabase

@Composable
fun ProfileScreen(
    user: UserModel,
    authViewModel: AuthViewModel,
    onUpdateSuccess: () -> Unit,
    onUpdateFailed: (String) -> Unit
) {
    val context = LocalContext.current
    var fullName by remember { mutableStateOf(user.fullName) }
    var email by remember { mutableStateOf(user.email) }
    var dateOfBirth by remember { mutableStateOf(user.dateOfBirth) }
    var gender by remember { mutableStateOf(user.gender) }
    var phoneNumber by remember { mutableStateOf(user.phoneNumber) }
    var isEditing by remember { mutableStateOf(false) }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isChangingPassword by remember { mutableStateOf(false) }
    var showLogoutDialog by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = { MyBottomBar(user = user, currentScreen = "Profile") },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.darkPurple2))
                .padding(paddingValues)
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ProfileTopBar(user = user)
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            colorResource(R.color.darkPurple),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(vertical = 16.dp, horizontal = 24.dp)
                ) {
                    YellowTitle("Personal Information")

                    Spacer(modifier = Modifier.height(16.dp))

                    // Full Name
                    OutlinedTextField(
                        value = fullName,
                        onValueChange = { if (isEditing) fullName = it },
                        label = { Text("Full Name", fontWeight = FontWeight.Bold) },
                        textStyle = LocalTextStyle.current.copy(fontWeight = FontWeight.Normal),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = colorResource(R.color.lightPurple),
                                shape = RoundedCornerShape(8.dp)
                            ),
                        enabled = isEditing,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedLabelColor = Color.Black,
                            unfocusedLabelColor = Color.Black,
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black,
                            disabledTextColor = Color.Black,
                            disabledLabelColor = Color.Black,
                            disabledBorderColor = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Email
                    OutlinedTextField(
                        value = email,
                        onValueChange = { if (isEditing) email = it },
                        label = { Text("Email", fontWeight = FontWeight.Bold) },
                        textStyle = LocalTextStyle.current.copy(fontWeight = FontWeight.Normal),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = colorResource(R.color.lightPurple),
                                shape = RoundedCornerShape(8.dp)
                            ),
                        enabled = isEditing,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedLabelColor = Color.Black,
                            unfocusedLabelColor = Color.Black,
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black,
                            disabledTextColor = Color.Black,
                            disabledLabelColor = Color.Black,
                            disabledBorderColor = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Date of Birth
                    OutlinedTextField(
                        value = dateOfBirth,
                        onValueChange = { if (isEditing) dateOfBirth = it },
                        label = { Text("Date of Birth (dd/mm/yyyy)", fontWeight = FontWeight.Bold) },
                        textStyle = LocalTextStyle.current.copy(fontWeight = FontWeight.Normal),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = colorResource(R.color.lightPurple),
                                shape = RoundedCornerShape(8.dp)
                            ),
                        enabled = isEditing,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedLabelColor = Color.Black,
                            unfocusedLabelColor = Color.Black,
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black,
                            disabledTextColor = Color.Black,
                            disabledLabelColor = Color.Black,
                            disabledBorderColor = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Gender
                    var expanded by remember { mutableStateOf(false) }
                    val genderOptions = listOf("Male", "Female", "Other")
                    Box {
                        OutlinedTextField(
                            value = gender,
                            onValueChange = {},
                            label = { Text("Gender", fontWeight = FontWeight.Bold) },
                            textStyle = LocalTextStyle.current.copy(fontWeight = FontWeight.Normal),
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = colorResource(R.color.lightPurple),
                                    shape = RoundedCornerShape(8.dp)
                                ),
                            enabled = false,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black,
                                focusedLabelColor = Color.Black,
                                unfocusedLabelColor = Color.Black,
                                focusedBorderColor = Color.Black,
                                unfocusedBorderColor = Color.Black,
                                disabledTextColor = Color.Black,
                                disabledLabelColor = Color.Black,
                                disabledBorderColor = Color.Black
                            )
                        )
                        if (isEditing) {
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                genderOptions.forEach { option ->
                                    DropdownMenuItem(
                                        text = { Text(option, color = Color.Black, fontWeight = FontWeight.Normal) },
                                        onClick = {
                                            gender = option
                                            expanded = false
                                        }
                                    )
                                }
                            }
                            Spacer(
                                modifier = Modifier
                                    .matchParentSize()
                                    .clickable { expanded = true }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Phone Number
                    OutlinedTextField(
                        value = phoneNumber,
                        onValueChange = { if (isEditing) phoneNumber = it },
                        label = { Text("Phone Number", fontWeight = FontWeight.Bold) },
                        textStyle = LocalTextStyle.current.copy(fontWeight = FontWeight.Normal),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = colorResource(R.color.lightPurple),
                                shape = RoundedCornerShape(8.dp)
                            ),
                        enabled = isEditing,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedLabelColor = Color.Black,
                            unfocusedLabelColor = Color.Black,
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black,
                            disabledTextColor = Color.Black,
                            disabledLabelColor = Color.Black,
                            disabledBorderColor = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Update Button
                    GradientButton(
                        onClick = {
                            if (!isEditing) {
                                isEditing = true
                            } else {
                                // Validate inputs
                                if (fullName.isEmpty()) {
                                    onUpdateFailed("Full Name is required")
                                    return@GradientButton
                                }
                                val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
                                if (!email.matches(emailRegex)) {
                                    onUpdateFailed("Invalid email format")
                                    return@GradientButton
                                }
                                if (dateOfBirth.isEmpty()) {
                                    onUpdateFailed("Date of Birth is required")
                                    return@GradientButton
                                }
                                if (gender.isEmpty()) {
                                    onUpdateFailed("Gender is required")
                                    return@GradientButton
                                }
                                val phoneRegex = Regex("^0[0-9]{9}$")
                                if (!phoneNumber.matches(phoneRegex)) {
                                    onUpdateFailed("Phone Number must be 10 digits and start with 0")
                                    return@GradientButton
                                }

                                // Update user info in Firebase
                                val updatedUser = UserModel(
                                    username = user.username,
                                    password = user.password,
                                    role = user.role,
                                    email = email,
                                    fullName = fullName,
                                    dateOfBirth = dateOfBirth,
                                    gender = gender,
                                    phoneNumber = phoneNumber
                                )
                                FirebaseDatabase.getInstance().getReference("Users")
                                    .child(user.username)
                                    .setValue(updatedUser)
                                    .addOnSuccessListener {
                                        isEditing = false
                                        onUpdateSuccess()
                                    }
                                    .addOnFailureListener {
                                        onUpdateFailed("Failed to update profile")
                                    }
                            }
                        },
                        text = if (isEditing) "Save" else "Update Profile"
                    )
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            colorResource(R.color.darkPurple),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(vertical = 16.dp, horizontal = 24.dp)
                ) {
                    YellowTitle("Account Information")

                    Spacer(modifier = Modifier.height(16.dp))

                    // Username
                    OutlinedTextField(
                        value = user.username,
                        onValueChange = {},
                        label = { Text("Username", fontWeight = FontWeight.Bold) },
                        textStyle = LocalTextStyle.current.copy(fontWeight = FontWeight.Normal),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = colorResource(R.color.lightPurple),
                                shape = RoundedCornerShape(8.dp)
                            ),
                        enabled = false,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedLabelColor = Color.Black,
                            unfocusedLabelColor = Color.Black,
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black,
                            disabledTextColor = Color.Black,
                            disabledLabelColor = Color.Black,
                            disabledBorderColor = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Password
                    OutlinedTextField(
                        value = "******",
                        onValueChange = {},
                        label = { Text("Password", fontWeight = FontWeight.Bold) },
                        textStyle = LocalTextStyle.current.copy(fontWeight = FontWeight.Normal),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = colorResource(R.color.lightPurple),
                                shape = RoundedCornerShape(8.dp)
                            ),
                        enabled = false,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedLabelColor = Color.Black,
                            unfocusedLabelColor = Color.Black,
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black,
                            disabledTextColor = Color.Black,
                            disabledLabelColor = Color.Black,
                            disabledBorderColor = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Change Password Button
                    GradientButton(
                        onClick = { isChangingPassword = true },
                        text = "Change Password"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Logout Button
                    GradientButton(
                        onClick = { showLogoutDialog = true },
                        text = "Logout"
                    )
                }
            }

            // Change Password Dialog
            if (isChangingPassword) {
                item {
                    AlertDialog(
                        onDismissRequest = { isChangingPassword = false },
                        title = { Text("Change Password", color = Color.Black, fontWeight = FontWeight.Bold) },
                        text = {
                            Column(
                                modifier = Modifier
                                    .background(
                                        color = colorResource(R.color.darkPurple),
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .padding(16.dp)
                            ) {
                                OutlinedTextField(
                                    value = newPassword,
                                    onValueChange = { newPassword = it },
                                    label = { Text("New Password", fontWeight = FontWeight.Bold) },
                                    textStyle = LocalTextStyle.current.copy(fontWeight = FontWeight.Normal),
                                    visualTransformation = PasswordVisualTransformation(),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            color = colorResource(R.color.lightPurple),
                                            shape = RoundedCornerShape(8.dp)
                                        ),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedTextColor = Color.Black,
                                        unfocusedTextColor = Color.Black,
                                        focusedLabelColor = Color.Black,
                                        unfocusedLabelColor = Color.Black,
                                        focusedBorderColor = Color.Black,
                                        unfocusedBorderColor = Color.Black
                                    )
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                OutlinedTextField(
                                    value = confirmPassword,
                                    onValueChange = { confirmPassword = it },
                                    label = { Text("Confirm Password", fontWeight = FontWeight.Bold) },
                                    textStyle = LocalTextStyle.current.copy(fontWeight = FontWeight.Normal),
                                    visualTransformation = PasswordVisualTransformation(),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            color = colorResource(R.color.lightPurple),
                                            shape = RoundedCornerShape(8.dp)
                                        ),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedTextColor = Color.Black,
                                        unfocusedTextColor = Color.Black,
                                        focusedLabelColor = Color.Black,
                                        unfocusedLabelColor = Color.Black,
                                        focusedBorderColor = Color.Black,
                                        unfocusedBorderColor = Color.Black
                                    )
                                )
                            }
                        },
                        confirmButton = {
                            GradientButton(
                                onClick = {
                                    val passwordRegex = Regex("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{6,}$")
                                    if (!newPassword.matches(passwordRegex)) {
                                        onUpdateFailed("Password must contain an uppercase letter, a number, and a special character")
                                        return@GradientButton
                                    }
                                    if (newPassword != confirmPassword) {
                                        onUpdateFailed("Passwords do not match")
                                        return@GradientButton
                                    }

                                    // Update password in Firebase
                                    val updatedUser = UserModel(
                                        username = user.username,
                                        password = newPassword,
                                        role = user.role,
                                        email = user.email,
                                        fullName = user.fullName,
                                        dateOfBirth = user.dateOfBirth,
                                        gender = user.gender,
                                        phoneNumber = user.phoneNumber
                                    )
                                    FirebaseDatabase.getInstance().getReference("Users")
                                        .child(user.username)
                                        .setValue(updatedUser)
                                        .addOnSuccessListener {
                                            isChangingPassword = false
                                            newPassword = ""
                                            confirmPassword = ""
                                            onUpdateSuccess()
                                        }
                                        .addOnFailureListener {
                                            onUpdateFailed("Failed to change password")
                                        }
                                },
                                text = "Save"
                            )
                        },
                        dismissButton = {
                            TextButton(onClick = { isChangingPassword = false }) {
                                Text("Cancel", color = Color.Black, fontWeight = FontWeight.Bold)
                            }
                        },
                        containerColor = colorResource(R.color.darkPurple2)
                    )
                }
            }

            // Logout Confirmation Dialog
            if (showLogoutDialog) {
                item {
                    AlertDialog(
                        onDismissRequest = { showLogoutDialog = false },
                        title = { Text("Logout", color = Color.Black, fontWeight = FontWeight.Bold) },
                        text = { Text("Are you sure you want to logout?", color = Color.Black, fontWeight = FontWeight.Normal) },
                        confirmButton = {
                            GradientButton(
                                onClick = {
                                    // Navigate to SplashActivity
                                    val intent = Intent(context, SplashActivity::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    context.startActivity(intent)
                                    // Finish current activity
                                    (context as? ProfileActivity)?.finish()
                                },
                                text = "Yes"
                            )
                        },
                        dismissButton = {
                            TextButton(onClick = { showLogoutDialog = false }) {
                                Text("No", color = Color.Black, fontWeight = FontWeight.Bold)
                            }
                        },
                        containerColor = colorResource(R.color.darkPurple2)
                    )
                }
            }

            // Spacer cuối để đảm bảo cuộn
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun ProfileTopBar(user: UserModel) {
    ConstraintLayout(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val (world, name, profile, notification, title) = createRefs()
        Image(
            painter = painterResource(R.drawable.world),
            contentDescription = null,
            modifier = Modifier
                .clickable { }
                .constrainAs(world) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )

        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(profile) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )

        Image(
            painter = painterResource(R.drawable.bell_icon),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(notification) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = user.fullName,
            color = Color.White,
            fontWeight = FontWeight.Normal, // Dữ liệu không in đậm
            fontSize = 14.sp,
            modifier = Modifier.constrainAs(name) {
                top.linkTo(parent.top)
                start.linkTo(profile.end)
                bottom.linkTo(parent.bottom)
            }
        )

        Text(
            text = "Profile",
            color = Color.White,
            fontWeight = FontWeight.Bold, // Nhãn in đậm
            fontSize = 25.sp,
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(profile.bottom)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}