package com.github.emilg1101.stackexchangeapp.questionssearch.model

import com.github.emilg1101.stackexchangeapp.domain.entity.Question

data class QuestionItemModel(
    val questionId: Int,
    val title: String,
    val ownerImage: String,
    val ownerName: String,
    val tags: List<String>
)

val QuestionItemModelsMapper: suspend (List<Question>) -> List<QuestionItemModel> = {
    it.map { question ->
        QuestionItemModel(
            question.id,
            question.title,
            question.owner.profileImage,
            question.owner.name,
            question.tags
        )
    }
}
