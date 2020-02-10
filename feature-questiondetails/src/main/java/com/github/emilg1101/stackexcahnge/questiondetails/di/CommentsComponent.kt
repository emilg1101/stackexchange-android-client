package com.github.emilg1101.stackexcahnge.questiondetails.di

import androidx.paging.PagedList
import com.github.emilg1101.stackexcahnge.questiondetails.paging.CommentsLivePagedListFactory
import com.github.emilg1101.stackexcahnge.questiondetails.ui.comments.CommentsViewModelFactory
import com.github.emilg1101.stackexchangeapp.domain.repository.CommentsRepository

internal interface CommentsComponent {

    val commentsRepository: CommentsRepository

    fun provideCommentsViewModelFactory(postId: Int): CommentsViewModelFactory

    companion object {
        fun create(dependencies: QuestionDetailsFeature.Dependencies) =
            CommentsModule(dependencies.commentsRepository)
    }
}

internal class CommentsModule(override val commentsRepository: CommentsRepository) : CommentsComponent {

    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(10)
        .build()

    private fun provideLivePagedListFactory(): CommentsLivePagedListFactory {
        return CommentsLivePagedListFactory(commentsRepository, config)
    }

    override fun provideCommentsViewModelFactory(postId: Int): CommentsViewModelFactory {
        return CommentsViewModelFactory(postId, provideLivePagedListFactory())
    }
}
