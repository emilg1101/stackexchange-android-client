package com.github.emilg1101.stackexchangeapp.app

import android.app.Application
import com.github.emilg1101.stackexchangeapp.app.di.AppInjector

class StackExchangeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }
}
