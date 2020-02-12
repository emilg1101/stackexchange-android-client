package com.github.emilg1101.stackexchangeapp.data.repository

import com.github.emilg1101.stackexchangeapp.data.api.StackExchangeService
import com.github.emilg1101.stackexchangeapp.data.api.entity.TagsResultEntityMapper
import com.github.emilg1101.stackexchangeapp.domain.repository.TagsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TagsRepositoryImpl(
    private val stackExchangeService: StackExchangeService
) : TagsRepository {

    override suspend fun getPopularTags(): Flow<List<String>> {
        return stackExchangeService.getTags()
            .map(TagsResultEntityMapper)
    }
}
