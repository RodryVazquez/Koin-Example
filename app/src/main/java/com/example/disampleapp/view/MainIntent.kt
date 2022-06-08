package com.example.disampleapp.view

import com.example.disampleapp.data.model.LoginBodyRequest

sealed class MainIntent {
    data class Login(val loginBodyRequest: LoginBodyRequest): MainIntent()
}