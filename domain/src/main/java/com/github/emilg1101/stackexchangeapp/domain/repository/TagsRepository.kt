package com.github.emilg1101.stackexchangeapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface TagsRepository {

    suspend fun getPopularTags(): Flow<List<String>>
}
