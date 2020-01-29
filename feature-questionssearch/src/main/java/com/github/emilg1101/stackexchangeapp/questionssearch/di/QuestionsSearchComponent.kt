package com.github.emilg1101.stackexchangeapp.questionssearch.di

import com.github.emilg1101.stackexchangeapp.domain.repository.QuestionsRepository
import com.github.emilg1101.stackexchangeapp.domain.repository.TagsRepository
import com.github.emilg1101.stackexchangeapp.questionssearch.ui.QuestionsSearchNavigation
import com.github.emilg1101.stackexchangeapp.questionssearch.ui.QuestionsSearchViewModelFactory

val QuestionsSearchComponentProvider = QuestionsSearchComponent.instance

abstract class QuestionsSearchComponent {

    abstract val helloWorld: String

    abstract val questionsRepository: QuestionsRepository

    abstract val tagsRepository: TagsRepository

    abstract val questionsSearchNavigation: QuestionsSearchNavigation

    fun provideViewModelFactory(): QuestionsSearchViewModelFactory =
        QuestionsSearchViewModelFactory(
            questionsRepository,
            tagsRepository,
            questionsSearchNavigation
        )

    companion object {
        lateinit var instance: QuestionsSearchComponent
    }
}
