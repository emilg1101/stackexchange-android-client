package com.github.emilg1101.stackexcahnge.questiondetails.di

import com.github.emilg1101.stackexcahnge.questiondetails.ui.question.QuestionDetailsNavigation
import com.github.emilg1101.stackexchangeapp.core.feature.Feature
import com.github.emilg1101.stackexchangeapp.domain.usecase.answers.GetAnswerUseCase
import com.github.emilg1101.stackexchangeapp.domain.usecase.answers.GetAnswersUseCase
import com.github.emilg1101.stackexchangeapp.domain.usecase.comments.GetCommentsUseCase
import com.github.emilg1101.stackexchangeapp.domain.usecase.questions.GetQuestionUseCase

val questionDetailsFeature: QuestionDetailsFeature = QuestionDetailsFeatureImpl()

interface QuestionDetailsFeature : Feature<QuestionDetailsFeature.Dependencies> {

    interface Dependencies {

        val getAnswerUseCase: GetAnswerUseCase

        val getAnswersUseCase: GetAnswersUseCase

        val getCommentsUseCase: GetCommentsUseCase

        val getQuestionUseCase: GetQuestionUseCase

        val navigation: QuestionDetailsNavigation
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
