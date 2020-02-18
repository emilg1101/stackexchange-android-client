package com.github.emilg1101.stackexchangeapp.data.repository

import com.github.emilg1101.stackexchangeapp.data.api.StackExchangeService
import com.github.emilg1101.stackexchangeapp.data.api.entity.UserResultEntityMapper
import com.github.emilg1101.stackexchangeapp.domain.entity.User
import com.github.emilg1101.stackexchangeapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val stackExchangeService: StackExchangeService
) : UserRepository {

    override fun getUser(userId: Int): Flow<User> {
        return stackExchangeService.getUserById(userId).map(UserResultEntityMapper)
    }
}
