//package com.example.ticketbookingapp.ViewModel
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import com.example.ticketbookingapp.Domain.UserModel
//import com.example.ticketbookingapp.Repository.AuthRepository
//
//class AuthViewModel : ViewModel() {
//    private val authRepository = AuthRepository()
//
//    fun login(username: String, password: String): LiveData<UserModel?> {
//        return authRepository.login(username, password)
//    }
//
//    fun register(username: String, password: String, role: String = "user"): LiveData<Boolean> {
//        return authRepository.register(username, password, role)
//    }
//}

package com.example.ticketbookingapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ticketbookingapp.Domain.UserModel
import com.example.ticketbookingapp.Repository.AuthRepository

class AuthViewModel : ViewModel() {
    private val authRepository = AuthRepository()

    fun login(identifier: String, password: String): LiveData<UserModel?> {
        return authRepository.login(identifier, password)
    }

    fun register(username: String, password: String, email: String, role: String = "user"): LiveData<Boolean> {
        return authRepository.register(username, password, email, role)
    }
}