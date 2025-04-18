//package com.example.ticketbookingapp.Activities.Auth
//
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
//}

package com.example.ticketbookingapp.Activities.Auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.ticketbookingapp.Activities.Dashboard.DashboardActivity
import com.example.ticketbookingapp.Activities.Splash.StatusTopBarColor
import com.example.ticketbookingapp.ViewModel.AuthViewModel
import com.example.ticketbookingapp.Domain.UserModel

class LoginActivity : AppCompatActivity() {
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        authViewModel = AuthViewModel()

        setContent {
            StatusTopBarColor()

            LoginScreen(
                authViewModel = authViewModel,
                onNavigateToRegister = {
                    startActivity(RegisterActivity.newIntent(this))
                },
                onLoginSuccess = { user ->
                    if (user.role == "admin") {
                        // TODO: Chuyển đến trang A (Admin Dashboard)
                        // startActivity(AdminDashboardActivity.newIntent(this))
                    } else {
                        startActivity(DashboardActivity.newIntent(this))
                        finish()
                    }
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