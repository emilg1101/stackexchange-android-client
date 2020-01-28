package com.github.emilg1101.stackexchangeapp.data.di

import com.github.emilg1101.stackexchangeapp.data.repository.AnswersRepositoryImpl
import com.github.emilg1101.stackexchangeapp.data.repository.QuestionsRepositoryImpl
import com.github.emilg1101.stackexchangeapp.data.repository.TagsRepositoryImpl
import com.github.emilg1101.stackexchangeapp.domain.repository.AnswersRepository
import com.github.emilg1101.stackexchangeapp.domain.repository.QuestionsRepository
import com.github.emilg1101.stackexchangeapp.domain.repository.TagsRepository

interface RepositoryComponent {

    val questionsRepository: QuestionsRepository

    val tagsRepository: TagsRepository

    val answersRepository: AnswersRepository

    companion object Factory {
        fun create(apiComponent: ApiComponent) = RepositoryModule(apiComponent)
    }
}

class RepositoryModule internal constructor(apiComponent: ApiComponent) : RepositoryComponent {

    override val questionsRepository: QuestionsRepository =
        QuestionsRepositoryImpl(apiComponent.stackExchangeService)

    override val tagsRepository: TagsRepository =
        TagsRepositoryImpl(apiComponent.stackExchangeService)

    override val answersRepository: AnswersRepository =
        AnswersRepositoryImpl(apiComponent.stackExchangeService)
}
