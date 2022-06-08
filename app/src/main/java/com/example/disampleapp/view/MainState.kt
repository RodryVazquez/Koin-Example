package com.example.disampleapp.view

sealed class MainState<out T> {
    data class Success<out T>(val data: T) : MainState<T>()
    data class Error(val error: String?) : MainState<Nothing>()
    object Loading : MainState<Nothing>()
}