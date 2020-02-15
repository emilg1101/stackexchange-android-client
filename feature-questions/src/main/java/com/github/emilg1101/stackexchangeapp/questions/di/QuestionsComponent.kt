package com.github.emilg1101.stackexchangeapp.questions.di

import androidx.paging.PagedList
import com.github.emilg1101.stackexchangeapp.domain.usecase.questions.GetQuestionsUseCase
import com.github.emilg1101.stackexchangeapp.domain.usecase.tags.GetPopularTagsUseCase
import com.github.emilg1101.stackexchangeapp.questions.adapter.QuestionsPagingAdapter
import com.github.emilg1101.stackexchangeapp.questions.paging.QuestionsLivePagedListFactory
import com.github.emilg1101.stackexchangeapp.questions.ui.QuestionsNavigation
import com.github.emilg1101.stackexchangeapp.questions.ui.QuestionsSearchFragment
import com.github.emilg1101.stackexchangeapp.questions.ui.QuestionsSearchViewModelFactory

internal interface QuestionsComponent {

    val getQuestionsUseCase: GetQuestionsUseCase

    val getPopularTagsUseCase: GetPopularTagsUseCase

    val questionsNavigation: QuestionsNavigation

    val questionsPagingAdapter: QuestionsPagingAdapter

    val viewModelFactory: QuestionsSearchViewModelFactory

    fun inject(fragment: QuestionsSearchFragment)

    companion object {
        fun create(dependencies: QuestionsFeature.Dependencies) =
            QuestionsModule(dependencies.getQuestionsUseCase, dependencies.getPopularTagsUseCase, dependencies.questionsNavigation)
    }
}

internal class QuestionsModule internal constructor(
    override val getQuestionsUseCase: GetQuestionsUseCase,
    override val getPopularTagsUseCase: GetPopularTagsUseCase,
    override val questionsNavigation: QuestionsNavigation
) : QuestionsComponent {

    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(10)
        .build()

    private val questionsLivePagedListFactory: QuestionsLivePagedListFactory = QuestionsLivePagedListFactory(getQuestionsUseCase, config)

    override val questionsPagingAdapter: QuestionsPagingAdapter = QuestionsPagingAdapter()

    override val viewModelFactory: QuestionsSearchViewModelFactory =
        QuestionsSearchViewModelFactory(
            questionsLivePagedListFactory,
            getPopularTagsUseCase,
            questionsNavigation
        )

    override fun inject(fragment: QuestionsSearchFragment) {
        fragment.adapter = questionsPagingAdapter
    }
}
