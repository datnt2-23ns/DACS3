//package com.example.ticketbookingapp.Activities.Auth
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import com.example.ticketbookingapp.Activities.Dashboard.DashboardActivity
//import com.example.ticketbookingapp.Activities.Splash.StatusTopBarColor
//import com.example.ticketbookingapp.ViewModel.AuthViewModel
//import com.example.ticketbookingapp.Domain.UserModel
//
//class LoginActivity : AppCompatActivity() {
//    private lateinit var authViewModel: AuthViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//
//        authViewModel = AuthViewModel()
//
//        setContent {
//            StatusTopBarColor()
//
//            LoginScreen(
//                authViewModel = authViewModel,
//                onNavigateToRegister = {
//                    startActivity(RegisterActivity.newIntent(this))
//                },
//                onLoginSuccess = { user ->
//                    if (user.role == "admin") {
//                        // TODO: Chuyển đến trang A (Admin Dashboard)
//                        // startActivity(AdminDashboardActivity.newIntent(this))
//                    } else {
//                        startActivity(DashboardActivity.newIntent(this))
//                        finish()
//                    }
//                }
//            )
//        }
//    }
//
//    companion object {
//        fun newIntent(context: Context): Intent {
//            return Intent(context, LoginActivity::class.java)
//        }
//    }
//}

//package com.example.ticketbookingapp.Activities.Auth
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import com.example.ticketbookingapp.Activities.Dashboard.DashboardActivity
//import com.example.ticketbookingapp.Activities.Splash.StatusTopBarColor
//import com.example.ticketbookingapp.ViewModel.AuthViewModel
//import com.example.ticketbookingapp.Domain.UserModel
//
//class LoginActivity : AppCompatActivity() {
//    private lateinit var authViewModel: AuthViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//
//        authViewModel = AuthViewModel()
//
//        setContent {
//            StatusTopBarColor()
//
//            LoginScreen(
//                authViewModel = authViewModel,
//                onNavigateToRegister = {
//                    startActivity(RegisterActivity.newIntent(this))
//                },
//                onLoginSuccess = { user ->
//                    if (user.role == "admin") {
//                        // TODO: Chuyển đến trang A (Admin Dashboard)
//                        // startActivity(AdminDashboardActivity.newIntent(this))
//                    } else {
//                        val intent = DashboardActivity.newIntent(this).apply {
//                            putExtra("user", user)
//                        }
//                        startActivity(intent)
//                        finish()
//                    }
//                }
//            )
//        }
//    }
//
//    companion object {
//        fun newIntent(context: Context): Intent {
//            return Intent(context, LoginActivity::class.java)
//        }
//    }
//}

package com.example.ticketbookingapp.Activities.Auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.ticketbookingapp.Activities.Dashboard.DashboardActivity
import com.example.ticketbookingapp.Activities.Splash.StatusTopBarColor
import com.example.ticketbookingapp.ViewModel.AuthViewModel
import com.example.ticketbookingapp.Domain.UserModel

class LoginActivity : AppCompatActivity() {
    private lateinit var authViewModel: AuthViewModel
    private val TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        authViewModel = AuthViewModel()

        setContent {
            StatusTopBarColor()

            LoginScreen(
                authViewModel = authViewModel,
                onNavigateToRegister = {
                    try {
                        Log.d(TAG, "Navigating to RegisterActivity")
                        startActivity(RegisterActivity.newIntent(this))
                    } catch (e: Exception) {
                        Log.e(TAG, "Error navigating to RegisterActivity: ${e.message}")
                        Toast.makeText(this, "Error: Unable to open Register screen", Toast.LENGTH_SHORT).show()
                    }
                },
                onLoginSuccess = { user ->
                    try {
                        Log.d(TAG, "Login success for user: ${user.username}")
                        if (user.role == "admin") {
                            // TODO: Chuyển đến trang A (Admin Dashboard)
                            Toast.makeText(this, "Admin login not implemented yet", Toast.LENGTH_SHORT).show()
                        } else {
                            val intent = DashboardActivity.newIntent(this).apply {
                                putExtra("user", user)
                                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                            }
                            startActivity(intent)
                            finish()
                        }
                    } catch (e: Exception) {
                        Log.e(TAG, "Error during login success: ${e.message}")
                        Toast.makeText(this, "Error: Unable to open Dashboard", Toast.LENGTH_SHORT).show()
                    }
                },
                onLoginFailed = { error ->
                    Log.d(TAG, "Login failed: $error")
                    Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}