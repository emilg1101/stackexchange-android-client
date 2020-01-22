package com.github.emilg1101.stackexchangeapp.data.api.entity

import com.github.emilg1101.stackexchangeapp.domain.entity.Question
import com.github.emilg1101.stackexchangeapp.domain.exceptions.QuestionNotFoundException
import com.google.gson.annotations.SerializedName

class QuestionsResultEntity {

    @field:SerializedName("items")
    val items: List<QuestionEntity> = listOf()
}

val QuestionsResultEntityMapper: suspend (QuestionsResultEntity) -> List<Question> = {
    it.items.map(QuestionEntityMapper)
}

val QuestionResultEntityMapper: suspend (QuestionsResultEntity) -> Question = {
    QuestionsResultEntityMapper(it).firstOrNull() ?: throw QuestionNotFoundException()
}
