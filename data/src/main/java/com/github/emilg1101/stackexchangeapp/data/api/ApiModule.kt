package com.github.emilg1101.stackexchangeapp.data.api

import com.github.emilg1101.stackexchangeapp.data.di.ApiComponent
import com.github.emilg1101.stackexchangeapp.data.extensions.FlowCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiModule(baseUrl: String) : ApiComponent {

    override val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()

    override val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(FlowCallAdapterFactory.create())
        .client(okHttpClient)
        .build()

    override val stackExchangeService: StackExchangeService =
        retrofit.create(StackExchangeService::class.java)
}
