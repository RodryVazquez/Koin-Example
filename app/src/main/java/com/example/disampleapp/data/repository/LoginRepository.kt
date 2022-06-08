package com.example.disampleapp.data.repository

import com.example.disampleapp.data.model.LoginResponse
import com.example.disampleapp.data.NetworkResult
import com.example.disampleapp.data.model.LoginBodyRequest

interface LoginRepository {
    suspend fun login(loginBodyRequest: LoginBodyRequest): NetworkResult<LoginResponse>
}