package com.github.emilg1101.stackexchangeapp.data.api.entity

import com.github.emilg1101.stackexchangeapp.data.extensions.toCalendar
import com.github.emilg1101.stackexchangeapp.domain.entity.Answer
import com.github.emilg1101.stackexchangeapp.domain.exceptions.AnswerNotFoundException
import com.google.gson.annotations.SerializedName

typealias ApiAnswer = com.github.emilg1101.stackexchangeapp.data.api.entity.Answer

data class Answer(
    @field:SerializedName("owner")
    val owner: User,
    @field:SerializedName("question_id")
    val questionId: Int,
    @field:SerializedName("answer_id")
    val answerId: Int,
    @field:SerializedName("body")
    val body: String?,
    @field:SerializedName("is_accepted")
    val isAccepted: Boolean,
    @field:SerializedName("comment_count")
    val commentCount: Int,
    @field:SerializedName("up_vote_count")
    val upVoteCount: Int,
    @field:SerializedName("down_vote_count")
    val downVoteCount: Int,
    @field:SerializedName("creation_date")
    val creationDate: Long
)

val AnswerEntityMapper: (ApiAnswer) -> Answer = { entity ->
    Answer(
        entity.answerId,
        entity.questionId,
        UserEntityMapper(entity.owner),
        entity.isAccepted,
        entity.commentCount,
        entity.upVoteCount,
        entity.downVoteCount,
        entity.creationDate.toCalendar()
    ).apply {
        body = entity.body.toString()
    }
}

val AnswersResultEntityMapper: suspend ResponseWrapper<ApiAnswer>.() -> List<Answer> = {
    items.map(AnswerEntityMapper)
}

val AnswerResultEntityMapper: suspend ResponseWrapper<ApiAnswer>.() -> Answer = {
    AnswersResultEntityMapper(this).firstOrNull() ?: throw AnswerNotFoundException()
}
