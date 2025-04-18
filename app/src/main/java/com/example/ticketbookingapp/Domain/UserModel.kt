package com.example.ticketbookingapp.Domain

import java.io.Serializable

data class UserModel(
    val username: String = "",
    val password: String = "",
    val role: String = "user", // Mặc định là user
    val email: String = ""
) : Serializable