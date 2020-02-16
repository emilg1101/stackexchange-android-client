package com.github.emilg1101.stackexcahnge.questiondetails.di

import androidx.paging.PagedList
import com.github.emilg1101.stackexcahnge.questiondetails.adapter.CommentsPagingAdapter
import com.github.emilg1101.stackexcahnge.questiondetails.paging.CommentsLivePagedListFactory
import com.github.emilg1101.stackexcahnge.questiondetails.ui.answer.AnswerDetailsFragment
import com.github.emilg1101.stackexcahnge.questiondetails.ui.answer.AnswerDetailsViewModelFactory
import com.github.emilg1101.stackexchangeapp.domain.usecase.answers.GetAnswerUseCase
import com.github.emilg1101.stackexchangeapp.domain.usecase.comments.GetCommentsUseCase

internal interface AnswerDetailsComponent {

    val getAnswerUseCase: GetAnswerUseCase

    val getCommentsUseCase: GetCommentsUseCase

    fun inject(fragment: AnswerDetailsFragment)

    companion object {
        fun create(dependencies: QuestionDetailsFeature.Dependencies) =
            AnswerDetailsModule(dependencies.getAnswerUseCase, dependencies.getCommentsUseCase)
    }
}

internal class AnswerDetailsModule(
    override val getAnswerUseCase: GetAnswerUseCase,
    override val getCommentsUseCase: GetCommentsUseCase
) : AnswerDetailsComponent {

    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(10)
        .build()

    private val livePagedListFactory: CommentsLivePagedListFactory =
        CommentsLivePagedListFactory(getCommentsUseCase, config)

    private val viewModelFactory: AnswerDetailsViewModelFactory =
        AnswerDetailsViewModelFactory(getAnswerUseCase, livePagedListFactory)

    private val adapter: CommentsPagingAdapter
        get() = CommentsPagingAdapter()

    override fun inject(fragment: AnswerDetailsFragment) {
        fragment.adapter = adapter
        fragment.viewModelFactory = viewModelFactory
    }
}
