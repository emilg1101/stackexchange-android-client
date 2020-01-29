package com.github.emilg1101.stackexchangeapp.app.di.module

import com.github.emilg1101.stackexcahnge.questiondetails.di.QuestionDetailsComponent
import com.github.emilg1101.stackexcahnge.questiondetails.ui.QuestionDetailsNavigation
import com.github.emilg1101.stackexchangeapp.app.ui.main.MainComponent
import com.github.emilg1101.stackexchangeapp.data.di.DataComponent
import com.github.emilg1101.stackexchangeapp.domain.repository.AnswersRepository
import com.github.emilg1101.stackexchangeapp.domain.repository.QuestionsRepository

class QuestionDetailsModule(dataComponent: DataComponent, mainComponent: MainComponent) : QuestionDetailsComponent() {

    override val questionsRepository: QuestionsRepository = dataComponent.repositoryComponent.questionsRepository

    override val answersRepository: AnswersRepository = dataComponent.repositoryComponent.answersRepository

    override val navigation: QuestionDetailsNavigation = mainComponent.navigator
}
