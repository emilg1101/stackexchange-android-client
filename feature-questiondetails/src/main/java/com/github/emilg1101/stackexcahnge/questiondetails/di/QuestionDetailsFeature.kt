package com.github.emilg1101.stackexcahnge.questiondetails.di

import com.github.emilg1101.stackexcahnge.questiondetails.ui.question.QuestionDetailsNavigation
import com.github.emilg1101.stackexchangeapp.core.feature.Feature
import com.github.emilg1101.stackexchangeapp.domain.repository.AnswersRepository
import com.github.emilg1101.stackexchangeapp.domain.repository.CommentsRepository
import com.github.emilg1101.stackexchangeapp.domain.repository.QuestionsRepository

val questionDetailsFeature: QuestionDetailsFeature = QuestionDetailsFeatureImpl()

interface QuestionDetailsFeature : Feature<QuestionDetailsFeature.Dependencies> {

    interface Dependencies {

        val questionsRepository: QuestionsRepository

        val answersRepository: AnswersRepository

        val navigation: QuestionDetailsNavigation

        val commentsRepository: CommentsRepository
    }
}

internal lateinit var questionDetailsComponent: QuestionDetailsComponent
    private set

internal lateinit var commentsComponent: CommentsComponent
    private set

internal lateinit var answerDetailsComponent: AnswerDetailsComponent
    private set

private class QuestionDetailsFeatureImpl internal constructor() : QuestionDetailsFeature {

    override fun inject(dependencies: QuestionDetailsFeature.Dependencies) {
        questionDetailsComponent = QuestionDetailsComponent.create(dependencies)
        commentsComponent = CommentsComponent.create(dependencies)
        answerDetailsComponent = AnswerDetailsComponent.create(dependencies)
    }
}
