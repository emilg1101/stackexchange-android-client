package com.github.emilg1101.stackexchangeapp.domain.repository

import com.github.emilg1101.stackexchangeapp.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUser(userId: Int): Flow<User>
}
