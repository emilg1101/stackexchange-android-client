package com.github.emilg1101.stackexchangeapp.questions.di

import com.github.emilg1101.stackexchangeapp.domain.repository.QuestionsRepository
import com.github.emilg1101.stackexchangeapp.domain.repository.TagsRepository
import com.github.emilg1101.stackexchangeapp.questions.adapter.QuestionsPagingAdapter
import com.github.emilg1101.stackexchangeapp.questions.ui.QuestionsSearchFragment
import com.github.emilg1101.stackexchangeapp.questions.ui.QuestionsNavigation
import com.github.emilg1101.stackexchangeapp.questions.ui.QuestionsSearchViewModelFactory

internal interface QuestionsComponent {

    val questionsRepository: QuestionsRepository

    val tagsRepository: TagsRepository

    val questionsNavigation: QuestionsNavigation

    val questionsPagingAdapter: QuestionsPagingAdapter

    fun provideViewModelFactory(): QuestionsSearchViewModelFactory

    fun inject(fragment: QuestionsSearchFragment)

    companion object {
        fun create(dependencies: QuestionsFeature.Dependencies) =
            QuestionsModule(dependencies.questionsRepository, dependencies.tagsRepository, dependencies.questionsNavigation)
    }
}

internal class QuestionsModule internal constructor(
    override val questionsRepository: QuestionsRepository,
    override val tagsRepository: TagsRepository,
    override val questionsNavigation: QuestionsNavigation
) : QuestionsComponent {

    override val questionsPagingAdapter: QuestionsPagingAdapter = QuestionsPagingAdapter()

    override fun inject(fragment: QuestionsSearchFragment) {
        fragment.adapter = questionsPagingAdapter
    }

    override fun provideViewModelFactory(): QuestionsSearchViewModelFactory {
        return QuestionsSearchViewModelFactory(
            questionsRepository,
            tagsRepository,
            questionsNavigation
        )
    }
}
