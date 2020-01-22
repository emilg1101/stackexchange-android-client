package com.github.emilg1101.stackexchangeapp.app.di

import android.app.Application
import android.content.Context

interface AppComponent {
    val context: Context

    companion object {
        fun create(application: Application) = AppModule(application)
    }
}

class AppModule internal constructor(application: Application) : AppComponent {

    override val context: Context = application.applicationContext
}
