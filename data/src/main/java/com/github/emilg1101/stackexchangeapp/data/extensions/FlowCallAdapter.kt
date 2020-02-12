package com.github.emilg1101.stackexchangeapp.data.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Response
import retrofit2.await
import retrofit2.awaitResponse
import java.lang.reflect.Type

class FlowCallAdapter<T : Any>(private val responseType: Type) : CallAdapter<T, Flow<T>> {

    override fun adapt(call: Call<T>): Flow<T> {
        return flow { emit(call.await()) }
    }

    override fun responseType(): Type = responseType
}

class FlowResponseCallAdapter<T : Any>(private val responseType: Type) : CallAdapter<T, Flow<Response<T>>> {

    override fun adapt(call: Call<T>): Flow<Response<T>> {
        return flow { emit(call.awaitResponse()) }
    }

    override fun responseType(): Type = responseType
}
