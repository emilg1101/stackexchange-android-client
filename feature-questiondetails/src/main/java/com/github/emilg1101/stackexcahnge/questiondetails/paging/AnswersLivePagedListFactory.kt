package com.github.emilg1101.stackexcahnge.questiondetails.paging

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.emilg1101.stackexcahnge.questiondetails.model.AnswerItemModel
import com.github.emilg1101.stackexchangeapp.domain.repository.AnswersRepository
import kotlinx.coroutines.CoroutineScope

class AnswersLivePagedListFactory(private val answersRepository: AnswersRepository, private val config: PagedList.Config) {

    fun create(questionId: Int, coroutineScope: CoroutineScope, callback: DataSourceStateCallback): LivePagedListBuilder<Long, AnswerItemModel> {
        val dataSourceFactory = AnswersDataSourceFactory(answersRepository, coroutineScope, questionId, callback)
        return LivePagedListBuilder(dataSourceFactory, config)
    }
}
