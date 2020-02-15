package com.github.emilg1101.stackexchangeapp.questions.di

import com.github.emilg1101.stackexchangeapp.core.feature.Feature
import com.github.emilg1101.stackexchangeapp.domain.usecase.questions.GetQuestionsUseCase
import com.github.emilg1101.stackexchangeapp.domain.usecase.tags.GetPopularTagsUseCase
import com.github.emilg1101.stackexchangeapp.questions.ui.QuestionsNavigation

val questionsFeature: QuestionsFeature = QuestionsFeatureImpl()

interface QuestionsFeature : Feature<QuestionsFeature.Dependencies> {

    interface Dependencies {

        val getQuestionsUseCase: GetQuestionsUseCase

        val getPopularTagsUseCase: GetPopularTagsUseCase

        val questionsNavigation: QuestionsNavigation
    }
}

internal lateinit var questionsComponent: QuestionsComponent
    private set

private class QuestionsFeatureImpl internal constructor() : QuestionsFeature {

    override fun inject(dependencies: QuestionsFeature.Dependencies) {
        questionsComponent = QuestionsComponent.create(dependencies)
    }
}
