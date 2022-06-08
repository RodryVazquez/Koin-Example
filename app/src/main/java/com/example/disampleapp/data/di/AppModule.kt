package com.example.disampleapp.data.di

import com.example.disampleapp.data.repository.LoginRepository
import com.example.disampleapp.data.repository.LoginRepositoryImpl
import com.example.disampleapp.data.service.LoginService
import com.example.disampleapp.util.Constants
import com.example.disampleapp.view.MainViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginService::class.java)
    }

    single(named("IODispatcher")) {
        Dispatchers.IO
    }

    single<LoginRepository>{
        LoginRepositoryImpl(get(named("IODispatcher")), get())
    }

    viewModel {
        MainViewModel(get())
    }
}