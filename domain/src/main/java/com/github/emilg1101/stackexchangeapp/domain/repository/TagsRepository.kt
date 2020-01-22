package com.github.emilg1101.stackexchangeapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface TagsRepository {

    fun getPopularTags(): Flow<List<String>>
}
