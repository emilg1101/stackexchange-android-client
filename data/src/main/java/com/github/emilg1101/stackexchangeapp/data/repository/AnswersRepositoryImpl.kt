package com.github.emilg1101.stackexchangeapp.data.repository

import com.github.emilg1101.stackexchangeapp.data.api.StackExchangeService
import com.github.emilg1101.stackexchangeapp.data.api.entity.AnswerResultEntityMapper
import com.github.emilg1101.stackexchangeapp.data.api.entity.AnswersResultEntityMapper
import com.github.emilg1101.stackexchangeapp.domain.entity.Answer
import com.github.emilg1101.stackexchangeapp.domain.repository.AnswersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AnswersRepositoryImpl(
    private val stackExchangeService: StackExchangeService
) : AnswersRepository {

    override suspend fun getAnswersByQuestionId(questionId: Int, page: Long): Flow<List<Answer>> {
        return stackExchangeService.getQuestionAnswers(questionId, page.toInt())
            .map(AnswersResultEntityMapper)
    }

    override suspend fun getAnswer(answerId: Int): Flow<Answer> {
        return stackExchangeService.getAnswerById(answerId)
            .map(AnswerResultEntityMapper)
    }
}
