package com.github.emilg1101.stackexchangeapp.data.api.entity

import com.github.emilg1101.stackexchangeapp.domain.entity.Owner
import com.github.emilg1101.stackexchangeapp.domain.entity.Question
import com.google.gson.annotations.SerializedName

class QuestionEntity(
    @field:SerializedName("owner")
    val owner: OwnerEntity,
    @field:SerializedName("question_id")
    val questionId: Int,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("body_markdown")
    val markdown: String?
) {
    @field:SerializedName("tags")
    val tags: List<String> = listOf()
}

val QuestionEntityMapper: (QuestionEntity) -> Question = { entity ->
    Question(
        entity.questionId,
        Owner(entity.owner.profileImage, entity.owner.displayName),
        entity.title
    ).apply {
        tags = entity.tags
        markdown = entity.markdown.toString()
    }
}
