package com.github.emilg1101.stackexcahnge.questiondetails.di

import androidx.paging.PagedList
import com.github.emilg1101.stackexcahnge.questiondetails.paging.CommentsLivePagedListFactory
import com.github.emilg1101.stackexcahnge.questiondetails.ui.answer.AnswerDetailsViewModelFactory
import com.github.emilg1101.stackexchangeapp.domain.repository.AnswersRepository
import com.github.emilg1101.stackexchangeapp.domain.repository.CommentsRepository

internal interface AnswerDetailsComponent {

    val answersRepository: AnswersRepository

    val commentsRepository: CommentsRepository

    fun provideAnswerDetailsViewModelFactory(): AnswerDetailsViewModelFactory

    companion object {
        fun create(dependencies: QuestionDetailsFeature.Dependencies) =
            AnswerDetailsModule(dependencies.answersRepository, dependencies.commentsRepository)
    }
}

internal class AnswerDetailsModule(
    override val answersRepository: AnswersRepository,
    override val commentsRepository: CommentsRepository
) : AnswerDetailsComponent {

    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(10)
        .build()

    private fun provideLivePagedListFactory(): CommentsLivePagedListFactory {
        return CommentsLivePagedListFactory(commentsRepository, config)
    }

    override fun provideAnswerDetailsViewModelFactory(): AnswerDetailsViewModelFactory {
        return AnswerDetailsViewModelFactory(answersRepository, provideLivePagedListFactory())
    }
}
