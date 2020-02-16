package com.github.emilg1101.stackexcahnge.questiondetails.di

import androidx.paging.PagedList
import com.github.emilg1101.stackexcahnge.questiondetails.adapter.AnswersPagingAdapter
import com.github.emilg1101.stackexcahnge.questiondetails.paging.AnswersLivePagedListFactory
import com.github.emilg1101.stackexcahnge.questiondetails.ui.question.QuestionDetailsFragment
import com.github.emilg1101.stackexcahnge.questiondetails.ui.question.QuestionDetailsNavigation
import com.github.emilg1101.stackexcahnge.questiondetails.ui.question.QuestionDetailsViewModelFactory
import com.github.emilg1101.stackexchangeapp.domain.usecase.answers.GetAnswersUseCase
import com.github.emilg1101.stackexchangeapp.domain.usecase.questions.GetQuestionUseCase

internal interface QuestionDetailsComponent {

    val getQuestionUseCase: GetQuestionUseCase

    val getAnswersUseCase: GetAnswersUseCase

    val navigation: QuestionDetailsNavigation

    fun inject(fragment: QuestionDetailsFragment)

    companion object {
        fun create(dependencies: QuestionDetailsFeature.Dependencies) =
            QuestionDetailsModule(dependencies.getQuestionUseCase, dependencies.getAnswersUseCase, dependencies.navigation)
    }
}

internal class QuestionDetailsModule(
    override val getQuestionUseCase: GetQuestionUseCase,
    override val getAnswersUseCase: GetAnswersUseCase,
    override val navigation: QuestionDetailsNavigation
) : QuestionDetailsComponent {

    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(10)
        .build()

    private val livePagedListFactory: AnswersLivePagedListFactory =
        AnswersLivePagedListFactory(getAnswersUseCase, config)

    private val viewModelFactory: QuestionDetailsViewModelFactory =
        QuestionDetailsViewModelFactory(
            getQuestionUseCase,
            navigation,
            livePagedListFactory
        )

    private val adapter: AnswersPagingAdapter
        get() = AnswersPagingAdapter()

    override fun inject(fragment: QuestionDetailsFragment) {
        fragment.viewModelFactory = viewModelFactory
        fragment.adapter = adapter
    }
}
