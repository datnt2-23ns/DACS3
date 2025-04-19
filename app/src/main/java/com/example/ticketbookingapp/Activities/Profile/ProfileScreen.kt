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
//import androidx.compose.ui.Alignment
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
//                .padding(horizontal = 16.dp),
//            verticalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            item {
//                Spacer(modifier = Modifier.height(16.dp))
//
//                ConstraintLayout(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(bottom = 16.dp)
//                ) {
//                    val (world, profileImg, fullNameText) = createRefs()
//
//                    Image(
//                        painter = painterResource(R.drawable.world),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .clickable { }
//                            .constrainAs(world) {
//                                top.linkTo(parent.top)
//                                bottom.linkTo(parent.bottom)
//                                start.linkTo(parent.start)
//                            }
//                    )
//
//                    Image(
//                        painter = painterResource(R.drawable.profile),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .size(80.dp)
//                            .constrainAs(profileImg) {
//                                top.linkTo(parent.top)
//                                start.linkTo(world.end, margin = 16.dp)
//                            }
//                    )
//
//                    Text(
//                        text = user.fullName,
//                        fontSize = 24.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.White,
//                        modifier = Modifier
//                            .constrainAs(fullNameText) {
//                                top.linkTo(profileImg.top)
//                                bottom.linkTo(profileImg.bottom)
//                                start.linkTo(profileImg.end, margin = 16.dp)
//                            }
//                    )
//                }
//
//                Text(
//                    text = "Personal Information",
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.SemiBold,
//                    color = colorResource(R.color.orange),
//                    modifier = Modifier.padding(bottom = 8.dp)
//                )
//
//                // Full Name
//                OutlinedTextField(
//                    value = fullName,
//                    onValueChange = { if (isEditing) fullName = it },
//                    label = { Text("Full Name") },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(
//                            color = colorResource(R.color.darkPurple),
//                            shape = RoundedCornerShape(12.dp)
//                        ),
//                    enabled = isEditing,
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White,
//                        focusedBorderColor = Color.White,
//                        unfocusedBorderColor = Color.White,
//                        disabledTextColor = Color.White,
//                        disabledLabelColor = Color.White,
//                        disabledBorderColor = Color.White
//                    )
//                )
//
//                // Email
//                OutlinedTextField(
//                    value = email,
//                    onValueChange = { if (isEditing) email = it },
//                    label = { Text("Email") },
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(
//                            color = colorResource(R.color.darkPurple),
//                            shape = RoundedCornerShape(12.dp)
//                        ),
//                    enabled = isEditing,
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White,
//                        focusedBorderColor = Color.White,
//                        unfocusedBorderColor = Color.White,
//                        disabledTextColor = Color.White,
//                        disabledLabelColor = Color.White,
//                        disabledBorderColor = Color.White
//                    )
//                )
//
//                // Date of Birth
//                OutlinedTextField(
//                    value = dateOfBirth,
//                    onValueChange = { if (isEditing) dateOfBirth = it },
//                    label = { Text("Date of Birth (dd/mm/yyyy)") },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(
//                            color = colorResource(R.color.darkPurple),
//                            shape = RoundedCornerShape(12.dp)
//                        ),
//                    enabled = isEditing,
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White,
//                        focusedBorderColor = Color.White,
//                        unfocusedBorderColor = Color.White,
//                        disabledTextColor = Color.White,
//                        disabledLabelColor = Color.White,
//                        disabledBorderColor = Color.White
//                    )
//                )
//
//                // Gender
//                var expanded by remember { mutableStateOf(false) }
//                val genderOptions = listOf("Male", "Female", "Other")
//                Box {
//                    OutlinedTextField(
//                        value = gender, // Sửa lỗi ở đây: thay 画像gender thành gender
//                        onValueChange = {},
//                        label = { Text("Gender") },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(
//                                color = colorResource(R.color.darkPurple),
//                                shape = RoundedCornerShape(12.dp)
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
//                    if (isEditing) {
//                        DropdownMenu(
//                            expanded = expanded,
//                            onDismissRequest = { expanded = false }
//                        ) {
//                            genderOptions.forEach { option ->
//                                DropdownMenuItem(
//                                    text = { Text(option) },
//                                    onClick = {
//                                        gender = option
//                                        expanded = false
//                                    }
//                                )
//                            }
//                        }
//                        Spacer(
//                            modifier = Modifier
//                                .matchParentSize()
//                                .clickable { expanded = true }
//                        )
//                    }
//                }
//
//                // Phone Number
//                OutlinedTextField(
//                    value = phoneNumber,
//                    onValueChange = { if (isEditing) phoneNumber = it },
//                    label = { Text("Phone Number") },
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(
//                            color = colorResource(R.color.darkPurple),
//                            shape = RoundedCornerShape(12.dp)
//                        ),
//                    enabled = isEditing,
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White,
//                        focusedBorderColor = Color.White,
//                        unfocusedBorderColor = Color.White,
//                        disabledTextColor = Color.White,
//                        disabledLabelColor = Color.White,
//                        disabledBorderColor = Color.White
//                    )
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // Update Button
//                GradientButton(
//                    onClick = {
//                        if (!isEditing) {
//                            isEditing = true
//                        } else {
//                            // Validate inputs
//                            if (fullName.isEmpty()) {
//                                onUpdateFailed("Full Name is required")
//                                return@GradientButton
//                            }
//                            val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
//                            if (!email.matches(emailRegex)) {
//                                onUpdateFailed("Invalid email format")
//                                return@GradientButton
//                            }
//                            if (dateOfBirth.isEmpty()) {
//                                onUpdateFailed("Date of Birth is required")
//                                return@GradientButton
//                            }
//                            if (gender.isEmpty()) {
//                                onUpdateFailed("Gender is required")
//                                return@GradientButton
//                            }
//                            val phoneRegex = Regex("^0[0-9]{9}$")
//                            if (!phoneNumber.matches(phoneRegex)) {
//                                onUpdateFailed("Phone Number must be 10 digits and start with 0")
//                                return@GradientButton
//                            }
//
//                            // Update user info in Firebase
//                            val updatedUser = UserModel(
//                                username = user.username,
//                                password = user.password,
//                                role = user.role,
//                                email = email,
//                                fullName = fullName,
//                                dateOfBirth = dateOfBirth,
//                                gender = gender,
//                                phoneNumber = phoneNumber
//                            )
//                            FirebaseDatabase.getInstance().getReference("Users")
//                                .child(user.username)
//                                .setValue(updatedUser)
//                                .addOnSuccessListener {
//                                    isEditing = false
//                                    onUpdateSuccess()
//                                }
//                                .addOnFailureListener {
//                                    onUpdateFailed("Failed to update profile")
//                                }
//                        }
//                    },
//                    text = if (isEditing) "Save" else "Update Profile",
//                    padding = 0
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Text(
//                    text = "Account Information",
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.SemiBold,
//                    color = colorResource(R.color.orange),
//                    modifier = Modifier.padding(bottom = 8.dp)
//                )
//
//                // Username
//                OutlinedTextField(
//                    value = user.username,
//                    onValueChange = {},
//                    label = { Text("Username") },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(
//                            color = colorResource(R.color.darkPurple),
//                            shape = RoundedCornerShape(12.dp)
//                        ),
//                    enabled = false,
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White,
//                        focusedBorderColor = Color.White,
//                        unfocusedBorderColor = Color.White,
//                        disabledTextColor = Color.White,
//                        disabledLabelColor = Color.White,
//                        disabledBorderColor = Color.White
//                    )
//                )
//
//                // Password
//                OutlinedTextField(
//                    value = "******",
//                    onValueChange = {},
//                    label = { Text("Password") },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(
//                            color = colorResource(R.color.darkPurple),
//                            shape = RoundedCornerShape(12.dp)
//                        ),
//                    enabled = false,
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White,
//                        focusedBorderColor = Color.White,
//                        unfocusedBorderColor = Color.White,
//                        disabledTextColor = Color.White,
//                        disabledLabelColor = Color.White,
//                        disabledBorderColor = Color.White
//                    )
//                )
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                // Change Password Button
//                GradientButton(
//                    onClick = { isChangingPassword = true },
//                    text = "Change Password",
//                    padding = 0
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//            }
//
//            // Thêm các Spacer giả để kiểm tra cuộn
//            items(5) {
//                Spacer(modifier = Modifier.height(100.dp))
//            }
//
//            // Change Password Dialog
//            if (isChangingPassword) {
//                item {
//                    AlertDialog(
//                        onDismissRequest = { isChangingPassword = false },
//                        title = { Text("Change Password") },
//                        text = {
//                            Column {
//                                OutlinedTextField(
//                                    value = newPassword,
//                                    onValueChange = { newPassword = it },
//                                    label = { Text("New Password") },
//                                    visualTransformation = PasswordVisualTransformation(),
//                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .background(
//                                            color = colorResource(R.color.darkPurple),
//                                            shape = RoundedCornerShape(12.dp)
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
//                                Spacer(modifier = Modifier.height(8.dp))
//                                OutlinedTextField(
//                                    value = confirmPassword,
//                                    onValueChange = { confirmPassword = it },
//                                    label = { Text("Confirm Password") },
//                                    visualTransformation = PasswordVisualTransformation(),
//                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .background(
//                                            color = colorResource(R.color.darkPurple),
//                                            shape = RoundedCornerShape(12.dp)
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
//                                text = "Save",
//                                padding = 0
//                            )
//                        },
//                        dismissButton = {
//                            TextButton(onClick = { isChangingPassword = false }) {
//                                Text("Cancel")
//                            }
//                        }
//                    )
//                }
//            }
//        }
//    }
//}

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
//                .padding(horizontal = 16.dp),
//            verticalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            item {
//                Spacer(modifier = Modifier.height(16.dp))
//
//                ConstraintLayout(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(bottom = 16.dp)
//                ) {
//                    val (world, profile, name, notification) = createRefs()
//
//                    // Icon world
//                    Image(
//                        painter = painterResource(R.drawable.world),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .clickable { }
//                            .constrainAs(world) {
//                                top.linkTo(parent.top)
//                                bottom.linkTo(parent.bottom)
//                                start.linkTo(parent.start)
//                            }
//                    )
//
//                    // Icon profile
//                    Image(
//                        painter = painterResource(R.drawable.profile),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .size(80.dp)
//                            .constrainAs(profile) {
//                                top.linkTo(parent.top)
//                                start.linkTo(world.end, margin = 16.dp)
//                            }
//                    )
//
//                    // Icon notification (bell_icon)
//                    Image(
//                        painter = painterResource(R.drawable.bell_icon),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .constrainAs(notification) {
//                                top.linkTo(parent.top)
//                                bottom.linkTo(parent.bottom)
//                                end.linkTo(parent.end)
//                            }
//                    )
//
//                    // Tên người dùng
//                    Text(
//                        text = user.fullName,
//                        fontSize = 24.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.White,
//                        modifier = Modifier
//                            .constrainAs(name) {
//                                top.linkTo(profile.top)
//                                bottom.linkTo(profile.bottom)
//                                start.linkTo(profile.end, margin = 16.dp)
//                            }
//                    )
//                }
//
//                Text(
//                    text = "Personal Information",
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.SemiBold,
//                    color = colorResource(R.color.orange),
//                    modifier = Modifier.padding(bottom = 8.dp)
//                )
//
//                // Full Name
//                OutlinedTextField(
//                    value = fullName,
//                    onValueChange = { if (isEditing) fullName = it },
//                    label = { Text("Full Name") },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(
//                            color = colorResource(R.color.darkPurple),
//                            shape = RoundedCornerShape(12.dp)
//                        ),
//                    enabled = isEditing,
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White,
//                        focusedBorderColor = Color.White,
//                        unfocusedBorderColor = Color.White,
//                        disabledTextColor = Color.White,
//                        disabledLabelColor = Color.White,
//                        disabledBorderColor = Color.White
//                    )
//                )
//
//                // Email
//                OutlinedTextField(
//                    value = email,
//                    onValueChange = { if (isEditing) email = it },
//                    label = { Text("Email") },
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(
//                            color = colorResource(R.color.darkPurple),
//                            shape = RoundedCornerShape(12.dp)
//                        ),
//                    enabled = isEditing,
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White,
//                        focusedBorderColor = Color.White,
//                        unfocusedBorderColor = Color.White,
//                        disabledTextColor = Color.White,
//                        disabledLabelColor = Color.White,
//                        disabledBorderColor = Color.White
//                    )
//                )
//
//                // Date of Birth
//                OutlinedTextField(
//                    value = dateOfBirth,
//                    onValueChange = { if (isEditing) dateOfBirth = it },
//                    label = { Text("Date of Birth (dd/mm/yyyy)") },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(
//                            color = colorResource(R.color.darkPurple),
//                            shape = RoundedCornerShape(12.dp)
//                        ),
//                    enabled = isEditing,
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White,
//                        focusedBorderColor = Color.White,
//                        unfocusedBorderColor = Color.White,
//                        disabledTextColor = Color.White,
//                        disabledLabelColor = Color.White,
//                        disabledBorderColor = Color.White
//                    )
//                )
//
//                // Gender
//                var expanded by remember { mutableStateOf(false) }
//                val genderOptions = listOf("Male", "Female", "Other")
//                Box {
//                    OutlinedTextField(
//                        value = gender,
//                        onValueChange = {},
//                        label = { Text("Gender") },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(
//                                color = colorResource(R.color.darkPurple),
//                                shape = RoundedCornerShape(12.dp)
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
//                    if (isEditing) {
//                        DropdownMenu(
//                            expanded = expanded,
//                            onDismissRequest = { expanded = false }
//                        ) {
//                            genderOptions.forEach { option ->
//                                DropdownMenuItem(
//                                    text = { Text(option) },
//                                    onClick = {
//                                        gender = option
//                                        expanded = false
//                                    }
//                                )
//                            }
//                        }
//                        Spacer(
//                            modifier = Modifier
//                                .matchParentSize()
//                                .clickable { expanded = true }
//                        )
//                    }
//                }
//
//                // Phone Number
//                OutlinedTextField(
//                    value = phoneNumber,
//                    onValueChange = { if (isEditing) phoneNumber = it },
//                    label = { Text("Phone Number") },
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(
//                            color = colorResource(R.color.darkPurple),
//                            shape = RoundedCornerShape(12.dp)
//                        ),
//                    enabled = isEditing,
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White,
//                        focusedBorderColor = Color.White,
//                        unfocusedBorderColor = Color.White,
//                        disabledTextColor = Color.White,
//                        disabledLabelColor = Color.White,
//                        disabledBorderColor = Color.White
//                    )
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // Update Button
//                GradientButton(
//                    onClick = {
//                        if (!isEditing) {
//                            isEditing = true
//                        } else {
//                            // Validate inputs
//                            if (fullName.isEmpty()) {
//                                onUpdateFailed("Full Name is required")
//                                return@GradientButton
//                            }
//                            val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
//                            if (!email.matches(emailRegex)) {
//                                onUpdateFailed("Invalid email format")
//                                return@GradientButton
//                            }
//                            if (dateOfBirth.isEmpty()) {
//                                onUpdateFailed("Date of Birth is required")
//                                return@GradientButton
//                            }
//                            if (gender.isEmpty()) {
//                                onUpdateFailed("Gender is required")
//                                return@GradientButton
//                            }
//                            val phoneRegex = Regex("^0[0-9]{9}$")
//                            if (!phoneNumber.matches(phoneRegex)) {
//                                onUpdateFailed("Phone Number must be 10 digits and start with 0")
//                                return@GradientButton
//                            }
//
//                            // Update user info in Firebase
//                            val updatedUser = UserModel(
//                                username = user.username,
//                                password = user.password,
//                                role = user.role,
//                                email = email,
//                                fullName = fullName,
//                                dateOfBirth = dateOfBirth,
//                                gender = gender,
//                                phoneNumber = phoneNumber
//                            )
//                            FirebaseDatabase.getInstance().getReference("Users")
//                                .child(user.username)
//                                .setValue(updatedUser)
//                                .addOnSuccessListener {
//                                    isEditing = false
//                                    onUpdateSuccess()
//                                }
//                                .addOnFailureListener {
//                                    onUpdateFailed("Failed to update profile")
//                                }
//                        }
//                    },
//                    text = if (isEditing) "Save" else "Update Profile",
//                    padding = 0
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Text(
//                    text = "Account Information",
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.SemiBold,
//                    color = colorResource(R.color.orange),
//                    modifier = Modifier.padding(bottom = 8.dp)
//                )
//
//                // Username
//                OutlinedTextField(
//                    value = user.username,
//                    onValueChange = {},
//                    label = { Text("Username") },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(
//                            color = colorResource(R.color.darkPurple),
//                            shape = RoundedCornerShape(12.dp)
//                        ),
//                    enabled = false,
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White,
//                        focusedBorderColor = Color.White,
//                        unfocusedBorderColor = Color.White,
//                        disabledTextColor = Color.White,
//                        disabledLabelColor = Color.White,
//                        disabledBorderColor = Color.White
//                    )
//                )
//
//                // Password
//                OutlinedTextField(
//                    value = "******",
//                    onValueChange = {},
//                    label = { Text("Password") },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(
//                            color = colorResource(R.color.darkPurple),
//                            shape = RoundedCornerShape(12.dp)
//                        ),
//                    enabled = false,
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedTextColor = Color.White,
//                        unfocusedTextColor = Color.White,
//                        focusedLabelColor = Color.White,
//                        unfocusedLabelColor = Color.White,
//                        focusedBorderColor = Color.White,
//                        unfocusedBorderColor = Color.White,
//                        disabledTextColor = Color.White,
//                        disabledLabelColor = Color.White,
//                        disabledBorderColor = Color.White
//                    )
//                )
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                // Change Password Button
//                GradientButton(
//                    onClick = { isChangingPassword = true },
//                    text = "Change Password",
//                    padding = 0
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//            }
//
//            // Change Password Dialog
//            if (isChangingPassword) {
//                item {
//                    AlertDialog(
//                        onDismissRequest = { isChangingPassword = false },
//                        title = { Text("Change Password") },
//                        text = {
//                            Column {
//                                OutlinedTextField(
//                                    value = newPassword,
//                                    onValueChange = { newPassword = it },
//                                    label = { Text("New Password") },
//                                    visualTransformation = PasswordVisualTransformation(),
//                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .background(
//                                            color = colorResource(R.color.darkPurple),
//                                            shape = RoundedCornerShape(12.dp)
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
//                                Spacer(modifier = Modifier.height(8.dp))
//                                OutlinedTextField(
//                                    value = confirmPassword,
//                                    onValueChange = { confirmPassword = it },
//                                    label = { Text("Confirm Password") },
//                                    visualTransformation = PasswordVisualTransformation(),
//                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .background(
//                                            color = colorResource(R.color.darkPurple),
//                                            shape = RoundedCornerShape(12.dp)
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
//                                text = "Save",
//                                padding = 0
//                            )
//                        },
//                        dismissButton = {
//                            TextButton(onClick = { isChangingPassword = false }) {
//                                Text("Cancel")
//                            }
//                        }
//                    )
//                }
//            }
//
//            // Thêm Spacer cuối để đảm bảo cuộn không bị cắt
//            item {
//                Spacer(modifier = Modifier.height(16.dp))
//            }
//        }
//    }
//}

package com.example.ticketbookingapp.Activities.Profile

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
import com.example.ticketbookingapp.Activities.Splash.GradientButton
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

    Scaffold(
        bottomBar = { MyBottomBar(user = user, currentScreen = "Profile") },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.darkPurple2))
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                ProfileTopBar(user = user)
            }

            item {
                Text(
                    text = "Personal Information",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(R.color.orange),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            item {
                // Full Name
                OutlinedTextField(
                    value = fullName,
                    onValueChange = { if (isEditing) fullName = it },
                    label = { Text("Full Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = colorResource(R.color.darkPurple),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    enabled = isEditing,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledTextColor = Color.White,
                        disabledLabelColor = Color.White,
                        disabledBorderColor = Color.White
                    )
                )
            }

            item {
                // Email
                OutlinedTextField(
                    value = email,
                    onValueChange = { if (isEditing) email = it },
                    label = { Text("Email") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = colorResource(R.color.darkPurple),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    enabled = isEditing,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledTextColor = Color.White,
                        disabledLabelColor = Color.White,
                        disabledBorderColor = Color.White
                    )
                )
            }

            item {
                // Date of Birth
                OutlinedTextField(
                    value = dateOfBirth,
                    onValueChange = { if (isEditing) dateOfBirth = it },
                    label = { Text("Date of Birth (dd/mm/yyyy)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = colorResource(R.color.darkPurple),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    enabled = isEditing,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledTextColor = Color.White,
                        disabledLabelColor = Color.White,
                        disabledBorderColor = Color.White
                    )
                )
            }

            item {
                // Gender
                var expanded by remember { mutableStateOf(false) }
                val genderOptions = listOf("Male", "Female", "Other")
                Box {
                    OutlinedTextField(
                        value = gender,
                        onValueChange = {},
                        label = { Text("Gender") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = colorResource(R.color.darkPurple),
                                shape = RoundedCornerShape(12.dp)
                            ),
                        enabled = false,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedLabelColor = Color.White,
                            unfocusedLabelColor = Color.White,
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White,
                            disabledTextColor = Color.White,
                            disabledLabelColor = Color.White,
                            disabledBorderColor = Color.White
                        )
                    )
                    if (isEditing) {
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            genderOptions.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option) },
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
            }

            item {
                // Phone Number
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { if (isEditing) phoneNumber = it },
                    label = { Text("Phone Number") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = colorResource(R.color.darkPurple),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    enabled = isEditing,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledTextColor = Color.White,
                        disabledLabelColor = Color.White,
                        disabledBorderColor = Color.White
                    )
                )
            }

            item {
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
                    text = if (isEditing) "Save" else "Update Profile",
                    padding = 0
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Account Information",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(R.color.orange),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            item {
                // Username
                OutlinedTextField(
                    value = user.username,
                    onValueChange = {},
                    label = { Text("Username") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = colorResource(R.color.darkPurple),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    enabled = false,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledTextColor = Color.White,
                        disabledLabelColor = Color.White,
                        disabledBorderColor = Color.White
                    )
                )
            }

            item {
                // Password
                OutlinedTextField(
                    value = "******",
                    onValueChange = {},
                    label = { Text("Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = colorResource(R.color.darkPurple),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    enabled = false,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        disabledTextColor = Color.White,
                        disabledLabelColor = Color.White,
                        disabledBorderColor = Color.White
                    )
                )
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))

                // Change Password Button
                GradientButton(
                    onClick = { isChangingPassword = true },
                    text = "Change Password",
                    padding = 0
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            // Change Password Dialog
            if (isChangingPassword) {
                item {
                    AlertDialog(
                        onDismissRequest = { isChangingPassword = false },
                        title = { Text("Change Password") },
                        text = {
                            Column {
                                OutlinedTextField(
                                    value = newPassword,
                                    onValueChange = { newPassword = it },
                                    label = { Text("New Password") },
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
                                OutlinedTextField(
                                    value = confirmPassword,
                                    onValueChange = { confirmPassword = it },
                                    label = { Text("Confirm Password") },
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
                                text = "Save",
                                padding = 0
                            )
                        },
                        dismissButton = {
                            TextButton(onClick = { isChangingPassword = false }) {
                                Text("Cancel")
                            }
                        }
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
            fontWeight = FontWeight.Bold,
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
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(profile.bottom)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}