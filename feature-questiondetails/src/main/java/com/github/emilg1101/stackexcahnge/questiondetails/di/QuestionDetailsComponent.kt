package com.github.emilg1101.stackexcahnge.questiondetails.di

import androidx.paging.PagedList
import com.github.emilg1101.stackexcahnge.questiondetails.paging.AnswersLivePagedListFactory
import com.github.emilg1101.stackexcahnge.questiondetails.ui.question.QuestionDetailsNavigation
import com.github.emilg1101.stackexcahnge.questiondetails.ui.question.QuestionDetailsViewModelFactory
import com.github.emilg1101.stackexchangeapp.domain.repository.AnswersRepository
import com.github.emilg1101.stackexchangeapp.domain.repository.QuestionsRepository

internal interface QuestionDetailsComponent {

    val questionsRepository: QuestionsRepository

    val answersRepository: AnswersRepository

    val navigation: QuestionDetailsNavigation

    fun provideViewModelFactory(): QuestionDetailsViewModelFactory

    companion object {
        fun create(dependencies: QuestionDetailsFeature.Dependencies) =
            QuestionDetailsModule(dependencies.questionsRepository, dependencies.answersRepository, dependencies.navigation)
    }
}

internal class QuestionDetailsModule(
    override val questionsRepository: QuestionsRepository,
    override val answersRepository: AnswersRepository,
    override val navigation: QuestionDetailsNavigation
) : QuestionDetailsComponent {

    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(10)
        .build()

    private fun provideLivePagedListFactory(): AnswersLivePagedListFactory {
        return AnswersLivePagedListFactory(answersRepository, config)
    }

    override fun provideViewModelFactory(): QuestionDetailsViewModelFactory {
        return QuestionDetailsViewModelFactory(
            questionsRepository,
            navigation,
            provideLivePagedListFactory()
        )
    }
}
