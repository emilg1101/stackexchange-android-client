package com.github.emilg1101.stackexchangeapp.questions.model

import com.github.emilg1101.stackexchangeapp.domain.entity.Question
import java.util.Calendar

data class QuestionItemModel(
    val questionId: Int,
    val title: String,
    val ownerId: Int,
    val ownerImage: String,
    val ownerName: String,
    val tags: List<String>,
    val date: Calendar
)

val QuestionItemModelsMapper: suspend (List<Question>) -> List<QuestionItemModel> = {
    it.map { question ->
        QuestionItemModel(
            question.id,
            question.title,
            question.owner.id,
            question.owner.profileImage,
            question.owner.name,
            question.tags,
            question.date
        )
    }
}
