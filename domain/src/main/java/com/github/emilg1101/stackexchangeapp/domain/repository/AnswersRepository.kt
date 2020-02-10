package com.github.emilg1101.stackexchangeapp.domain.repository

import com.github.emilg1101.stackexchangeapp.domain.entity.Answer
import kotlinx.coroutines.flow.Flow

interface AnswersRepository {

    fun getAnswersByQuestionId(questionId: Int, page: Long): Flow<List<Answer>>

    fun getAnswer(answerId: Int): Flow<Answer>
}
