package com.example.disampleapp

import android.app.Application
import com.example.disampleapp.data.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DISampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DISampleApplication)
            modules(appModule)
        }
    }
}