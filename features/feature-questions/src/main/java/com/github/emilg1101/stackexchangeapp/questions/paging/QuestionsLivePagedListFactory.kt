package com.github.emilg1101.stackexchangeapp.questions.paging

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.emilg1101.stackexchangeapp.domain.usecase.questions.GetQuestionsUseCase
import com.github.emilg1101.stackexchangeapp.questions.model.QuestionItemModel
import com.github.emilg1101.stackexchangeapp.questions.ui.Sort
import kotlinx.coroutines.CoroutineScope

internal class QuestionsLivePagedListFactory(private val getQuestionsUseCase: GetQuestionsUseCase, private val config: PagedList.Config) {

    fun create(
        sort: Sort,
        tags: List<String>,
        coroutineScope: CoroutineScope,
        callback: DataSourceStateCallback
    ): LivePagedListBuilder<Long, QuestionItemModel> {
        val dataSourceFactory = QuestionsDataSourceFactory(getQuestionsUseCase, coroutineScope, sort, tags, callback)
        return LivePagedListBuilder(dataSourceFactory, config)
    }
}
