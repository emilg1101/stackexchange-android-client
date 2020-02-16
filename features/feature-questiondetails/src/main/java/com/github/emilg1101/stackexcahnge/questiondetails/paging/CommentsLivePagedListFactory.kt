package com.github.emilg1101.stackexcahnge.questiondetails.paging

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.emilg1101.stackexcahnge.questiondetails.model.CommentItemModel
import com.github.emilg1101.stackexchangeapp.domain.usecase.comments.GetCommentsUseCase
import kotlinx.coroutines.CoroutineScope

class CommentsLivePagedListFactory(private val getCommentsUseCase: GetCommentsUseCase, private val config: PagedList.Config) {

    fun create(questionId: Int, coroutineScope: CoroutineScope, callback: DataSourceStateCallback): LivePagedListBuilder<Long, CommentItemModel> {
        val dataSourceFactory = CommentsDataSourceFactory(getCommentsUseCase, coroutineScope, questionId, callback)
        return LivePagedListBuilder(dataSourceFactory, config)
    }
}
