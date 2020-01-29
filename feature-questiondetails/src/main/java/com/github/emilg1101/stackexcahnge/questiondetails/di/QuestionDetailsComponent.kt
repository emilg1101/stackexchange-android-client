package com.github.emilg1101.stackexcahnge.questiondetails.di

import androidx.paging.PagedList
import com.github.emilg1101.stackexcahnge.questiondetails.paging.LivePagedListFactory
import com.github.emilg1101.stackexcahnge.questiondetails.ui.QuestionDetailsNavigation
import com.github.emilg1101.stackexcahnge.questiondetails.ui.QuestionDetailsViewModelFactory
import com.github.emilg1101.stackexchangeapp.domain.repository.AnswersRepository
import com.github.emilg1101.stackexchangeapp.domain.repository.QuestionsRepository

val QuestionDetailsComponentProvider = QuestionDetailsComponent.instance

abstract class QuestionDetailsComponent {

    abstract val questionsRepository: QuestionsRepository

    abstract val answersRepository: AnswersRepository

    abstract val navigation: QuestionDetailsNavigation

    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(10)
        .build()

    private fun provideLivePagedListFactory(): LivePagedListFactory {
        return LivePagedListFactory(answersRepository, config)
    }

    fun provideViewModelFactory(questionId: Int): QuestionDetailsViewModelFactory {
        return QuestionDetailsViewModelFactory(questionId, questionsRepository, navigation, provideLivePagedListFactory())
    }

    companion object {
        lateinit var instance: QuestionDetailsComponent
    }
}
