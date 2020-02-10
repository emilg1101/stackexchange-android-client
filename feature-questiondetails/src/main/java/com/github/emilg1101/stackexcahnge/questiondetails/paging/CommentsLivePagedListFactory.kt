package com.github.emilg1101.stackexcahnge.questiondetails.paging

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.emilg1101.stackexcahnge.questiondetails.model.CommentItemModel
import com.github.emilg1101.stackexchangeapp.domain.repository.CommentsRepository
import kotlinx.coroutines.CoroutineScope

class CommentsLivePagedListFactory(private val commentsRepository: CommentsRepository, private val config: PagedList.Config) {

    fun create(questionId: Int, coroutineScope: CoroutineScope, callback: DataSourceStateCallback): LivePagedListBuilder<Long, CommentItemModel> {
        val dataSourceFactory = CommentsDataSourceFactory(commentsRepository, coroutineScope, questionId, callback)
        return LivePagedListBuilder(dataSourceFactory, config)
    }
}
