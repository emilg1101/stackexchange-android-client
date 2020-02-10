package com.github.emilg1101.stackexcahnge.questiondetails.model

import com.github.emilg1101.stackexchangeapp.core.extensions.format
import com.github.emilg1101.stackexchangeapp.domain.entity.Question

data class QuestionDetailsModel(
    val questionId: Int,
    val title: String,
    val ownerImage: String,
    val ownerName: String,
    val tags: List<String>,
    val markdown: String,
    val answerCount: Int,
    val date: String,
    val hasComments: Boolean
)

val QuestionDetailsModelMapper: suspend (Question) -> QuestionDetailsModel = { question ->
    QuestionDetailsModel(
        question.id,
        question.title,
        question.owner.profileImage,
        question.owner.name,
        question.tags,
        question.markdown,
        question.answerCount,
        question.date.format("E d MMM yyyy HH:mm"),
        question.commentCount > 0
    )
}
