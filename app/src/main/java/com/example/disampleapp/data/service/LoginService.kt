package com.example.disampleapp.data.service

import com.example.disampleapp.data.model.LoginBodyRequest
import com.example.disampleapp.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("api/login")
    suspend fun login(@Body loginBodyRequest: LoginBodyRequest): LoginResponse
}