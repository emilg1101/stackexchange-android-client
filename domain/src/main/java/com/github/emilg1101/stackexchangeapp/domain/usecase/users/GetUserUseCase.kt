package com.github.emilg1101.stackexchangeapp.domain.usecase.users

import com.github.emilg1101.stackexchangeapp.domain.FlowUseCase
import com.github.emilg1101.stackexchangeapp.domain.entity.User
import com.github.emilg1101.stackexchangeapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(
    private val userRepository: UserRepository
) : FlowUseCase<User, GetUserUseCase.Params>() {

    override suspend fun run(params: Params): Flow<User> =
        userRepository.getUser(params.userId)

    data class Params(val userId: Int)
}
