package com.github.emilg1101.stackexchangeapp.data.repository

import com.github.emilg1101.stackexchangeapp.data.api.StackExchangeService
import com.github.emilg1101.stackexchangeapp.data.api.entity.QuestionResultEntityMapper
import com.github.emilg1101.stackexchangeapp.data.api.entity.QuestionsResultEntityMapper
import com.github.emilg1101.stackexchangeapp.domain.entity.Question
import com.github.emilg1101.stackexchangeapp.domain.repository.QuestionsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class QuestionsRepositoryImpl(
    private val stackExchangeService: StackExchangeService
) : QuestionsRepository {

    override fun getQuestions(
        page: Long,
        sort: String,
        tags: List<String>
    ): Flow<List<Question>> {
        return flow {
            emit(
                stackExchangeService.getQuestions(
                    page.toInt() + 1,
                    sort = sort,
                    tags = tags.joinToString(separator = ";").ifEmpty { null }
                )
            )
        }.map(QuestionsResultEntityMapper)
    }

    override fun getQuestion(questionsId: Int): Flow<Question> {
        return flow { emit(stackExchangeService.getQuestionById(questionsId)) }
            .map(QuestionResultEntityMapper)
    }
}
