package com.github.emilg1101.stackexchangeapp.domain.repository

import com.github.emilg1101.stackexchangeapp.domain.entity.Question
import kotlinx.coroutines.flow.Flow

interface QuestionsRepository {

    suspend fun getQuestions(
        page: Long,
        sort: String,
        tags: List<String>
    ): Flow<List<Question>>

    suspend fun getQuestion(questionsId: Int): Flow<Question>
}
