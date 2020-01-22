package com.github.emilg1101.stackexcahnge.questiondetails.di

import com.github.emilg1101.stackexcahnge.questiondetails.ui.QuestionDetailsViewModelFactory
import com.github.emilg1101.stackexchangeapp.domain.repository.QuestionsRepository

val QuestionDetailsComponentProvider = QuestionDetailsComponent.instance

abstract class QuestionDetailsComponent {

    abstract val questionsRepository: QuestionsRepository

    fun provideViewModelFactory(questionId: Int): QuestionDetailsViewModelFactory {
        return QuestionDetailsViewModelFactory(questionId, questionsRepository)
    }

    companion object {
        lateinit var instance: QuestionDetailsComponent
    }
}
