package com.github.emilg1101.stackexcahnge.questiondetails.ui.question

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.github.emilg1101.stackexcahnge.questiondetails.model.QuestionDetailsModelMapper
import com.github.emilg1101.stackexcahnge.questiondetails.paging.DataSourceStateCallback
import com.github.emilg1101.stackexcahnge.questiondetails.paging.AnswersLivePagedListFactory
import com.github.emilg1101.stackexchangeapp.core.ui.base.BaseViewModel
import com.github.emilg1101.stackexchangeapp.domain.repository.QuestionsRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion

class QuestionDetailsViewModel internal constructor(
    private val questionsRepository: QuestionsRepository,
    private val navigation: QuestionDetailsNavigation,
    private val livePagedListFactory: AnswersLivePagedListFactory
) : BaseViewModel(), DataSourceStateCallback {

    private val _progress = MutableLiveData(true)

    val progress: LiveData<Boolean>
        get() = _progress

    private val _questionId = MutableLiveData<Int>()

    val questionDetails = _questionId.switchMap { id ->
        viewModelScope.async {
            questionsRepository.getQuestion(id)
                .map(QuestionDetailsModelMapper)
                .onCompletion { _progress.value = false }
                .catch { _snackbar.value = it.message }
                .asLiveData()
        }.getCompleted()
    }

    var pagedListLiveData = _questionId.switchMap { id ->
        val livePagedListBuilder = livePagedListFactory.create(id, viewModelScope, this)
        livePagedListBuilder.build()
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

    fun openQuestionComments() {
        _questionId.value?.let { navigation.openQuestionComments(it) }
    }

    fun setQuestionId(questionId: Int) {
        if (_questionId.value != questionId) {
            _questionId.value = questionId
        }
    }

    fun openAnswerComments(answerId: Int) {
        navigation.openQuestionComments(answerId)
    }

    fun openAnswerDetails(answerId: Int) {
        navigation.openAnswer(answerId)
    }
}
