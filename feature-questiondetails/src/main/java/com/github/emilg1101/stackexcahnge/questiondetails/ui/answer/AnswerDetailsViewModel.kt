package com.github.emilg1101.stackexcahnge.questiondetails.ui.answer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.github.emilg1101.stackexcahnge.questiondetails.model.AnswerDetailsModelMapper
import com.github.emilg1101.stackexcahnge.questiondetails.paging.CommentsLivePagedListFactory
import com.github.emilg1101.stackexcahnge.questiondetails.paging.DataSourceStateCallback
import com.github.emilg1101.stackexchangeapp.core.ui.base.BaseViewModel
import com.github.emilg1101.stackexchangeapp.domain.repository.AnswersRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion

class AnswerDetailsViewModel(
    private val answersRepository: AnswersRepository,
    private val livePagedListFactory: CommentsLivePagedListFactory
) : BaseViewModel(), DataSourceStateCallback {

    private val _progress = MutableLiveData(true)

    val progress: LiveData<Boolean>
        get() = _progress

    private val _answerId = MutableLiveData<Int>()

    val answerDetails = _answerId.switchMap { answerId ->
        answersRepository.getAnswer(answerId)
            .map(AnswerDetailsModelMapper)
            .onCompletion { _progress.value = false }
            .catch { _snackbar.value = it.message }
            .asLiveData()
    }

    var pagedListLiveData = _answerId.switchMap { answerId ->
        val livePagedListBuilder = livePagedListFactory.create(answerId, viewModelScope, this)
        livePagedListBuilder.build()
    }

    fun setAnswerId(answerId: Int) {
        if (_answerId.value != answerId) {
            _answerId.value = answerId
        }
    }

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
