package com.github.emilg1101.stackexchangeapp.data.api.entity

import com.github.emilg1101.stackexchangeapp.domain.entity.Answer
import com.google.gson.annotations.SerializedName

class AnswersResultEntity {

    @field:SerializedName("items")
    val items: List<AnswerEntity> = listOf()
}

val AnswersResultEntityMapper: suspend (AnswersResultEntity) -> List<Answer> = {
    it.items.map(AnswerEntityMapper)
}
