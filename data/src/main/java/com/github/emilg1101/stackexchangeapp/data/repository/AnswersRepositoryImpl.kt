package com.github.emilg1101.stackexchangeapp.data.repository

import com.github.emilg1101.stackexchangeapp.data.api.StackExchangeService
import com.github.emilg1101.stackexchangeapp.data.api.entity.AnswerResultEntityMapper
import com.github.emilg1101.stackexchangeapp.data.api.entity.AnswersResultEntityMapper
import com.github.emilg1101.stackexchangeapp.domain.entity.Answer
import com.github.emilg1101.stackexchangeapp.domain.repository.AnswersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class AnswersRepositoryImpl(
    private val stackExchangeService: StackExchangeService
) : AnswersRepository {

    override fun getAnswersByQuestionId(questionId: Int, page: Long): Flow<List<Answer>> {
        return flow {
            emit(stackExchangeService.getQuestionAnswers(questionId, page.toInt()))
        }.map(AnswersResultEntityMapper)
    }

    override fun getAnswer(answerId: Int): Flow<Answer> {
        return flow {
            emit(stackExchangeService.getAnswerById(answerId))
        }.map(AnswerResultEntityMapper)
    }
}
