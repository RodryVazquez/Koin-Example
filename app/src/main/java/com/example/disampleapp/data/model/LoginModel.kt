package com.example.disampleapp.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val success: Boolean,
    val data: UserData,
    val message: String
)

data class UserData(
    val userName: String,
    val token: String,
    val firstName: String,
    val lastName: String
)

data class LoginBodyRequest(
    val email: String,
    val password: String
)