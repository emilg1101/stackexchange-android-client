package com.github.emilg1101.stackexchangeapp.domain

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Flow<Type>

    suspend operator fun invoke(params: Params): Flow<Type> {
        return run(params)
    }
}
