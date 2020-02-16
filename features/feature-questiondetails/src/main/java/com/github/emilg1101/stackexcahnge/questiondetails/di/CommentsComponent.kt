package com.github.emilg1101.stackexcahnge.questiondetails.di

import androidx.paging.PagedList
import com.github.emilg1101.stackexcahnge.questiondetails.adapter.CommentsPagingAdapter
import com.github.emilg1101.stackexcahnge.questiondetails.paging.CommentsLivePagedListFactory
import com.github.emilg1101.stackexcahnge.questiondetails.ui.comments.CommentsFragment
import com.github.emilg1101.stackexcahnge.questiondetails.ui.comments.CommentsViewModelFactory
import com.github.emilg1101.stackexchangeapp.domain.usecase.comments.GetCommentsUseCase

internal interface CommentsComponent {

    val getCommentsUseCase: GetCommentsUseCase

    fun inject(fragment: CommentsFragment)

    companion object {
        fun create(dependencies: QuestionDetailsFeature.Dependencies) =
            CommentsModule(dependencies.getCommentsUseCase)
    }
}

internal class CommentsModule(override val getCommentsUseCase: GetCommentsUseCase) : CommentsComponent {

    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(10)
        .build()

    private val livePagedListFactory: CommentsLivePagedListFactory =
        CommentsLivePagedListFactory(getCommentsUseCase, config)

    private val viewModelFactory: CommentsViewModelFactory = CommentsViewModelFactory(livePagedListFactory)

    private val adapter: CommentsPagingAdapter
        get() = CommentsPagingAdapter()

    override fun inject(fragment: CommentsFragment) {
        fragment.adapter = adapter
        fragment.viewModelFactory = viewModelFactory
    }
}
