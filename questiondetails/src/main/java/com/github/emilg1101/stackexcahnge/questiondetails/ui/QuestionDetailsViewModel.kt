package com.github.emilg1101.stackexcahnge.questiondetails.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.github.emilg1101.stackexcahnge.questiondetails.model.QuestionDetailsModelMapper
import com.github.emilg1101.stackexcahnge.questiondetails.paging.DataSourceStateCallback
import com.github.emilg1101.stackexcahnge.questiondetails.paging.LivePagedListFactory
import com.github.emilg1101.stackexchangeapp.core.ui.base.BaseViewModel
import com.github.emilg1101.stackexchangeapp.domain.repository.QuestionsRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion

class QuestionDetailsViewModel internal constructor(
    private val questionId: Int,
    private val questionsRepository: QuestionsRepository,
    private val livePagedListFactory: LivePagedListFactory
) : BaseViewModel(), DataSourceStateCallback {

    private val _progress = MutableLiveData(true)

    val progress: LiveData<Boolean>
        get() = _progress

    val questionDetails = questionsRepository.getQuestion(questionId)
        .map(QuestionDetailsModelMapper)
        .onCompletion { _progress.value = false }
        .catch { _snackbar.value = it.message }
        .asLiveData()

    private val livePagedListBuilder = livePagedListFactory.create(questionId, viewModelScope, this)

    var pagedListLiveData = livePagedListBuilder.build()

    override suspend fun onDataLoading() {
    }

    override suspend fun onDataLoaded() {
    }

    override suspend fun onDataEmpty() {
    }

    override suspend fun onError(t: Throwable) {
        _snackbar.value = t.message
    }
}
