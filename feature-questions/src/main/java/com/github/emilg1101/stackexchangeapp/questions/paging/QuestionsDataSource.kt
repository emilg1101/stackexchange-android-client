package com.github.emilg1101.stackexchangeapp.questions.paging

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.github.emilg1101.stackexchangeapp.domain.repository.QuestionsRepository
import com.github.emilg1101.stackexchangeapp.questions.model.QuestionItemModel
import com.github.emilg1101.stackexchangeapp.questions.model.QuestionItemModelsMapper
import com.github.emilg1101.stackexchangeapp.questions.ui.Sort
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

class QuestionsDataSource internal constructor(
    private val questionsRepository: QuestionsRepository,
    private val coroutineScope: CoroutineScope,
    private val sort: Sort,
    private val tags: List<String>,
    private val stateCallback: DataSourceStateCallback
) : PageKeyedDataSource<Long, QuestionItemModel>() {

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, QuestionItemModel>
    ) {
        coroutineScope.launch {
            stateCallback.onDataLoading()
            questionsRepository.getQuestions(0, sort.sort, tags)
                .map(QuestionItemModelsMapper)
                .catch { stateCallback.onError(it) }
                .onCompletion { stateCallback.onDataLoaded() }
                .collect { result ->
                    if (result.isEmpty()) {
                        stateCallback.onDataEmpty()
                    } else {
                        callback.onResult(result, null, 1)
                    }
                }
        }
    }

    override fun loadAfter(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, QuestionItemModel>
    ) {
        coroutineScope.launch {
            questionsRepository.getQuestions(params.key, sort.sort, tags)
                .map(QuestionItemModelsMapper)
                .catch { stateCallback.onError(it) }
                .collect { result ->
                    callback.onResult(result, params.key + 1)
                }
        }
    }

    override fun loadBefore(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, QuestionItemModel>
    ) {
    }
}

internal class QuestionsDataSourceFactory(
    private val questionsRepository: QuestionsRepository,
    private val coroutineScope: CoroutineScope,
    private val sort: Sort,
    private val tags: List<String>,
    private val callback: DataSourceStateCallback
) : DataSource.Factory<Long, QuestionItemModel>() {

    override fun create(): DataSource<Long, QuestionItemModel> {
        return QuestionsDataSource(
            questionsRepository,
            coroutineScope,
            sort,
            tags,
            callback
        )
    }
}

interface DataSourceStateCallback {

    suspend fun onDataLoading()

    suspend fun onDataLoaded()

    suspend fun onError(t: Throwable)

    suspend fun onDataEmpty()
}
