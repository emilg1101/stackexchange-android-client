package com.github.emilg1101.stackexchangeapp.data.di

import com.github.emilg1101.stackexchangeapp.data.api.ApiModule
import com.github.emilg1101.stackexchangeapp.data.api.StackExchangeService
import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface ApiComponent {

    val okHttpClient: OkHttpClient

    val retrofit: Retrofit

    val stackExchangeService: StackExchangeService

    companion object {
        fun create(baseUrl: String) = ApiModule(baseUrl)
    }
}
