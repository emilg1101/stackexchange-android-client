package com.github.emilg1101.stackexchangeapp.domain.usecase.answers

import com.github.emilg1101.stackexchangeapp.domain.FlowUseCase
import com.github.emilg1101.stackexchangeapp.domain.entity.Answer
import com.github.emilg1101.stackexchangeapp.domain.repository.AnswersRepository
import kotlinx.coroutines.flow.Flow

class GetAnswerUseCase(
    private val answersRepository: AnswersRepository
) : FlowUseCase<Answer, GetAnswerUseCase.Params>() {

    override suspend fun run(params: Params): Flow<Answer> =
        answersRepository.getAnswer(params.answerId)

    data class Params(val answerId: Int)
}
