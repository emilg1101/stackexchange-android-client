package com.github.emilg1101.stackexcahnge.questiondetails.paging

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.github.emilg1101.stackexcahnge.questiondetails.model.AnswerItemModel
import com.github.emilg1101.stackexcahnge.questiondetails.model.AnswerItemModelsMapper
import com.github.emilg1101.stackexchangeapp.domain.repository.AnswersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

class AnswersDataSource internal constructor(
    private val answersRepository: AnswersRepository,
    private val coroutineScope: CoroutineScope,
    private val questionId: Int,
    private val stateCallback: DataSourceStateCallback
) : PageKeyedDataSource<Long, AnswerItemModel>() {

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, AnswerItemModel>
    ) {
        coroutineScope.launch {
            stateCallback.onDataLoading()
            answersRepository.getAnswersByQuestionId(questionId, 1)
                .map(AnswerItemModelsMapper)
                .catch { stateCallback.onError(it) }
                .onCompletion { stateCallback.onDataLoaded() }
                .collect { result ->
                    if (result.isEmpty()) {
                        stateCallback.onDataEmpty()
                    } else {
                        callback.onResult(result, null, 2)
                    }
                }
        }
    }

    override fun loadAfter(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, AnswerItemModel>
    ) {
        coroutineScope.launch {
            answersRepository.getAnswersByQuestionId(questionId, params.key)
                .map(AnswerItemModelsMapper)
                .catch { stateCallback.onError(it) }
                .collect { result ->
                    callback.onResult(result, params.key + 1)
                }
        }
    }

    override fun loadBefore(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, AnswerItemModel>
    ) {
    }
}

class AnswersDataSourceFactory(
    private val answersRepository: AnswersRepository,
    private val coroutineScope: CoroutineScope,
    private val questionId: Int,
    private val callback: DataSourceStateCallback
) : DataSource.Factory<Long, AnswerItemModel>() {

    override fun create(): DataSource<Long, AnswerItemModel> {
        return AnswersDataSource(
            answersRepository,
            coroutineScope,
            questionId,
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
