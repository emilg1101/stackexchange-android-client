package com.github.emilg1101.stackexcahnge.questiondetails.ui.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.emilg1101.stackexcahnge.questiondetails.paging.CommentsLivePagedListFactory
import com.github.emilg1101.stackexcahnge.questiondetails.paging.DataSourceStateCallback
import com.github.emilg1101.stackexchangeapp.core.ui.base.BaseViewModel

class CommentsViewModel internal constructor(
    private val postId: Int,
    private val livePagedListFactory: CommentsLivePagedListFactory
) : BaseViewModel(), DataSourceStateCallback {

    private val _progress = MutableLiveData(true)

    val progress: LiveData<Boolean>
        get() = _progress

    private val livePagedListBuilder = livePagedListFactory.create(postId, viewModelScope, this)

    var pagedListLiveData = livePagedListBuilder.build()

    override suspend fun onDataLoading() {
        _progress.value = true
    }

    override suspend fun onDataLoaded() {
        _progress.value = false
    }

    override suspend fun onDataEmpty() {
        _progress.value = false
        _snackbar.value = "empty"
    }

    override suspend fun onError(t: Throwable) {
        _snackbar.value = t.message
    }
}
