package com.github.emilg1101.stackexchangeapp.app.di.module

import com.github.emilg1101.stackexcahnge.questiondetails.di.QuestionDetailsComponent
import com.github.emilg1101.stackexchangeapp.data.di.DataComponent
import com.github.emilg1101.stackexchangeapp.data.repository.QuestionsRepositoryImpl
import com.github.emilg1101.stackexchangeapp.domain.repository.QuestionsRepository

class QuestionDetailsModule(dataComponent: DataComponent) : QuestionDetailsComponent() {

    override val questionsRepository: QuestionsRepository =
        QuestionsRepositoryImpl(dataComponent.apiComponent.stackExchangeService)
}