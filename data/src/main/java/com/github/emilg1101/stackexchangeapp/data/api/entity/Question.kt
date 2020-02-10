package com.github.emilg1101.stackexchangeapp.data.api.entity

import com.github.emilg1101.stackexchangeapp.data.extensions.toCalendar
import com.github.emilg1101.stackexchangeapp.domain.entity.Question
import com.github.emilg1101.stackexchangeapp.domain.exceptions.QuestionNotFoundException
import com.google.gson.annotations.SerializedName

typealias ApiQuestion = com.github.emilg1101.stackexchangeapp.data.api.entity.Question

data class Question(
    @field:SerializedName("owner")
    val owner: User,
    @field:SerializedName("question_id")
    val questionId: Int,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("body")
    val body: String?,
    @field:SerializedName("answer_count")
    val answerCount: Int = 0,
    @field:SerializedName("creation_date")
    val creationDate: Long,
    @field:SerializedName("comment_count")
    val commentCount: Int = 0
) {
    @field:SerializedName("tags")
    val tags: List<String> = listOf()
}

val QuestionEntityMapper: (ApiQuestion) -> Question = { entity ->
    Question(
        entity.questionId,
        OwnerEntityMapper(entity.owner),
        entity.title,
        entity.answerCount,
        entity.creationDate.toCalendar()
    ).apply {
        tags = entity.tags
        markdown = entity.body.toString()
        commentCount = entity.commentCount
    }
}

val QuestionsResultEntityMapper: suspend ResponseWrapper<ApiQuestion>.() -> List<Question> = {
    items.map(QuestionEntityMapper)
}

val QuestionResultEntityMapper: suspend ResponseWrapper<ApiQuestion>.() -> Question = {
    QuestionsResultEntityMapper(this).firstOrNull() ?: throw QuestionNotFoundException()
}
