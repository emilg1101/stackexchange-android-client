package com.github.emilg1101.stackexchangeapp.domain.usecase.questions

import com.github.emilg1101.stackexchangeapp.domain.FlowUseCase
import com.github.emilg1101.stackexchangeapp.domain.entity.Question
import com.github.emilg1101.stackexchangeapp.domain.repository.QuestionsRepository
import kotlinx.coroutines.flow.Flow

class GetQuestionUseCase(
    private val questionsRepository: QuestionsRepository
) : FlowUseCase<Question, GetQuestionUseCase.Params>() {

    override suspend fun run(params: Params): Flow<Question> =
        questionsRepository.getQuestion(params.questionId)

    data class Params(val questionId: Int)
}
