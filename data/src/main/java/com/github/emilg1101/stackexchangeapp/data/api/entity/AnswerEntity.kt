package com.github.emilg1101.stackexchangeapp.data.api.entity

import com.github.emilg1101.stackexchangeapp.domain.entity.Answer
import com.github.emilg1101.stackexchangeapp.domain.entity.Owner
import com.google.gson.annotations.SerializedName

class AnswerEntity(
    @field:SerializedName("owner")
    val owner: OwnerEntity,
    @field:SerializedName("question_id")
    val questionId: Int,
    @field:SerializedName("answer_id")
    val answerId: Int,
    @field:SerializedName("body_markdown")
    val markdown: String?,
    @field:SerializedName("is_accepted")
    val isAccepted: Boolean
)

val AnswerEntityMapper: (AnswerEntity) -> Answer = { entity ->
    Answer(
        entity.answerId,
        entity.questionId,
        Owner(entity.owner.profileImage, entity.owner.displayName),
        entity.isAccepted
    ).apply {
        markdown = entity.markdown.toString()
    }
}
