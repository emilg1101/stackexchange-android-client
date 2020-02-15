package com.github.emilg1101.stackexchangeapp.domain.usecase.tags

import com.github.emilg1101.stackexchangeapp.domain.FlowUseCase
import com.github.emilg1101.stackexchangeapp.domain.repository.TagsRepository
import kotlinx.coroutines.flow.Flow

class GetPopularTagsUseCase(
    private val tagsRepository: TagsRepository
) : FlowUseCase<List<String>, GetPopularTagsUseCase.Params>() {

    override suspend fun run(params: Params): Flow<List<String>> =
        tagsRepository.getPopularTags()

    suspend operator fun invoke() = super.invoke(Params())

    data class Params(val tagsCount: Int = 40)
}
