package com.github.emilg1101.stackexcahnge.questiondetails.paging

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.emilg1101.stackexcahnge.questiondetails.model.AnswerItemModel
import com.github.emilg1101.stackexchangeapp.domain.usecase.answers.GetAnswersUseCase
import kotlinx.coroutines.CoroutineScope

class AnswersLivePagedListFactory(private val getAnswersUseCase: GetAnswersUseCase, private val config: PagedList.Config) {

    fun create(questionId: Int, coroutineScope: CoroutineScope, callback: DataSourceStateCallback): LivePagedListBuilder<Long, AnswerItemModel> {
        val dataSourceFactory = AnswersDataSourceFactory(getAnswersUseCase, coroutineScope, questionId, callback)
        return LivePagedListBuilder(dataSourceFactory, config)
    }
}
