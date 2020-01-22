package com.github.emilg1101.stackexcahnge.questiondetails.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.github.emilg1101.stackexcahnge.questiondetails.model.QuestionDetailsModelMapper
import com.github.emilg1101.stackexchangeapp.core.ui.base.BaseViewModel
import com.github.emilg1101.stackexchangeapp.domain.repository.QuestionsRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion

class QuestionDetailsViewModel internal constructor(
    private val questionId: Int,
    private val questionsRepository: QuestionsRepository
) : BaseViewModel() {

    private val _progress = MutableLiveData(true)

    val progress: LiveData<Boolean>
        get() = _progress

    val questionDetails = questionsRepository.getQuestion(questionId)
        .map(QuestionDetailsModelMapper)
        .onCompletion { _progress.value = false }
        .catch { _snackbar.value = it.message }
        .asLiveData()
}
