package com.example.disampleapp.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disampleapp.data.NetworkResult
import com.example.disampleapp.data.model.LoginBodyRequest
import com.example.disampleapp.data.model.LoginResponse
import com.example.disampleapp.data.repository.LoginRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val loginRepository: LoginRepository
): ViewModel () {

    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)

    private val _loginState: MutableLiveData<MainState<LoginResponse>> = MutableLiveData()
    val loginState: LiveData<MainState<LoginResponse>>
        get() = _loginState

    init {
        handleIntent()
    }

    private fun handleIntent(){
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when(it ){
                    is MainIntent.Login -> {
                        login(it.loginBodyRequest)
                    }
                }
            }
        }
    }

    private fun login(loginBodyRequest: LoginBodyRequest){
        viewModelScope.launch {
            _loginState.postValue(MainState.Loading)
            when (val response = loginRepository.login(loginBodyRequest)) {
                is NetworkResult.Success -> {
                    _loginState.postValue(MainState.Success(response.value))
                }

                is NetworkResult.Error -> {
                    _loginState.postValue(MainState.Error("Login Error ${response.exception.stackTrace}"))
                }
            }
        }
    }
}