package com.example.carinfo_test

import android.app.Application
import com.example.carinfo_test.di.databaseModule
import com.example.carinfo_test.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CarInfoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CarInfoApp)
            modules(listOf(viewModelModule, databaseModule))
        }
    }
}