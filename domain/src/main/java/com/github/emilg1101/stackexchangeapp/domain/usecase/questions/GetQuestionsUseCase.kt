package com.github.emilg1101.stackexchangeapp.domain.usecase.questions

import com.github.emilg1101.stackexchangeapp.domain.FlowUseCase
import com.github.emilg1101.stackexchangeapp.domain.entity.Question
import com.github.emilg1101.stackexchangeapp.domain.repository.QuestionsRepository
import kotlinx.coroutines.flow.Flow

class GetQuestionsUseCase(
    private val questionsRepository: QuestionsRepository
) : FlowUseCase<List<Question>, GetQuestionsUseCase.Params>() {

    override suspend fun run(params: Params): Flow<List<Question>> =
        questionsRepository.getQuestions(params.page, params.sort, params.tags)

    data class Params(
        val page: Long,
        val sort: String,
        val tags: List<String>
    )
}
