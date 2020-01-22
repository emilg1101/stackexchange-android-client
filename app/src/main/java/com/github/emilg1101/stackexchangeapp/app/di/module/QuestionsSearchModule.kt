package com.github.emilg1101.stackexchangeapp.app.di.module

import com.github.emilg1101.stackexchangeapp.app.di.AppComponent
import com.github.emilg1101.stackexchangeapp.app.ui.main.MainComponent
import com.github.emilg1101.stackexchangeapp.data.di.DataComponent
import com.github.emilg1101.stackexchangeapp.data.repository.QuestionsRepositoryImpl
import com.github.emilg1101.stackexchangeapp.data.repository.TagsRepositoryImpl
import com.github.emilg1101.stackexchangeapp.domain.repository.QuestionsRepository
import com.github.emilg1101.stackexchangeapp.domain.repository.TagsRepository
import com.github.emilg1101.stackexchangeapp.questionssearch.di.QuestionsSearchComponent
import com.github.emilg1101.stackexchangeapp.questionssearch.ui.QuestionsSearchNavigation

class QuestionsSearchModule(
    appComponent: AppComponent,
    dataComponent: DataComponent,
    mainComponent: MainComponent
) : QuestionsSearchComponent() {

    override val helloWorld: String = appComponent.context.applicationInfo.name

    override val questionsRepository: QuestionsRepository =
        QuestionsRepositoryImpl(dataComponent.apiComponent.stackExchangeService)

    override val questionsSearchNavigation: QuestionsSearchNavigation = mainComponent.navigator

    override val tagsRepository: TagsRepository =
        TagsRepositoryImpl(dataComponent.apiComponent.stackExchangeService)
}
