package com.github.emilg1101.stackexcahnge.questiondetails.paging

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.github.emilg1101.stackexcahnge.questiondetails.model.CommentItemModel
import com.github.emilg1101.stackexcahnge.questiondetails.model.CommentItemModelMapper
import com.github.emilg1101.stackexchangeapp.domain.usecase.comments.GetCommentsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

class CommentsDataSource internal constructor(
    private val getCommentsUseCase: GetCommentsUseCase,
    private val coroutineScope: CoroutineScope,
    private val postId: Int,
    private val stateCallback: DataSourceStateCallback
) : PageKeyedDataSource<Long, CommentItemModel>() {

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, CommentItemModel>
    ) {
        coroutineScope.launch {
            stateCallback.onDataLoading()
            getCommentsUseCase(GetCommentsUseCase.Params(postId))
                .map(CommentItemModelMapper)
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
        callback: LoadCallback<Long, CommentItemModel>
    ) {
        coroutineScope.launch {
            getCommentsUseCase(GetCommentsUseCase.Params(postId, params.key))
                .map(CommentItemModelMapper)
                .catch { stateCallback.onError(it) }
                .collect { result ->
                    callback.onResult(result, params.key + 1)
                }
        }
    }

    override fun loadBefore(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, CommentItemModel>
    ) {
    }
}

class CommentsDataSourceFactory(
    private val getCommentsUseCase: GetCommentsUseCase,
    private val coroutineScope: CoroutineScope,
    private val postId: Int,
    private val callback: DataSourceStateCallback
) : DataSource.Factory<Long, CommentItemModel>() {

    override fun create(): DataSource<Long, CommentItemModel> {
        return CommentsDataSource(
            getCommentsUseCase,
            coroutineScope,
            postId,
            callback
        )
    }
}
