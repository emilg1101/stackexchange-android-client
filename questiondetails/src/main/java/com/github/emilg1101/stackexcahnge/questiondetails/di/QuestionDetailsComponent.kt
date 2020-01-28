package com.github.emilg1101.stackexcahnge.questiondetails.di

import androidx.paging.PagedList
import com.github.emilg1101.stackexcahnge.questiondetails.paging.LivePagedListFactory
import com.github.emilg1101.stackexcahnge.questiondetails.ui.QuestionDetailsViewModelFactory
import com.github.emilg1101.stackexchangeapp.domain.repository.AnswersRepository
import com.github.emilg1101.stackexchangeapp.domain.repository.QuestionsRepository

val QuestionDetailsComponentProvider = QuestionDetailsComponent.instance

abstract class QuestionDetailsComponent {

    abstract val questionsRepository: QuestionsRepository

    abstract val answersRepository: AnswersRepository

    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(10)
        .build()

    private fun provideLivePagedListFactory(): LivePagedListFactory {
        return LivePagedListFactory(answersRepository, config)
    }

    fun provideViewModelFactory(questionId: Int): QuestionDetailsViewModelFactory {
        return QuestionDetailsViewModelFactory(questionId, questionsRepository, provideLivePagedListFactory())
    }

    companion object {
        lateinit var instance: QuestionDetailsComponent
    }
}
