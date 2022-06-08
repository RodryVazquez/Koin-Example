package com.example.disampleapp.data.repository

import com.example.disampleapp.data.model.LoginBodyRequest
import com.example.disampleapp.data.model.LoginResponse
import com.example.disampleapp.data.NetworkResult
import com.example.disampleapp.data.service.LoginService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepositoryImpl(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val loginService: LoginService,
) : LoginRepository {

    override suspend fun login(loginBodyRequest: LoginBodyRequest): NetworkResult<LoginResponse> {
        return withContext(defaultDispatcher) {
            try {
                NetworkResult.Success(loginService.login(loginBodyRequest))
            }catch (error: Throwable){
                NetworkResult.Error(Exception(error.stackTraceToString()))
            }
        }
    }
}