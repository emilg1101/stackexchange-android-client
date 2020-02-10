package com.github.emilg1101.stackexcahnge.questiondetails.model

import com.github.emilg1101.stackexchangeapp.core.extensions.format
import com.github.emilg1101.stackexchangeapp.domain.entity.Answer

data class AnswerDetailsModel(
    val answerId: Int,
    val ownerImage: String,
    val ownerName: String,
    val markdown: String,
    val date: String
)

val AnswerDetailsModelMapper: suspend Answer.() -> AnswerDetailsModel = {
    AnswerDetailsModel(id, owner.profileImage, owner.name, body, date.format("E d MMM yyyy HH:mm"))
}
