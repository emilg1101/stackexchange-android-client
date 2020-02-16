package com.github.emilg1101.stackexchangeapp.domain.usecase.answers

import com.github.emilg1101.stackexchangeapp.domain.FlowUseCase
import com.github.emilg1101.stackexchangeapp.domain.entity.Answer
import com.github.emilg1101.stackexchangeapp.domain.repository.AnswersRepository
import kotlinx.coroutines.flow.Flow

class GetAnswersUseCase(
    private val answersRepository: AnswersRepository
) : FlowUseCase<List<Answer>, GetAnswersUseCase.Params>() {

    override suspend fun run(params: Params): Flow<List<Answer>> =
        answersRepository.getAnswersByQuestionId(params.questionId, params.page)

    data class Params(val questionId: Int, val page: Long = 1)
}
