package com.github.emilg1101.stackexcahnge.questiondetails.model

import com.github.emilg1101.stackexchangeapp.domain.entity.Answer
import java.util.Calendar

data class AnswerItemModel(
    val answerId: Int,
    val questionId: Int,
    val ownerImage: String,
    val ownerName: String,
    val body: String,
    val accepted: Boolean,
    val upVoteCount: Int,
    val downVoteCount: Int,
    val commentCount: Int,
    val date: Calendar
)

val AnswerItemModelsMapper: suspend (List<Answer>) -> List<AnswerItemModel> = {
    it.map { answer ->
        AnswerItemModel(
            answer.id,
            answer.questionId,
            answer.owner.profileImage,
            answer.owner.name,
            answer.body,
            answer.accepted,
            answer.upVoteCount,
            answer.downVoteCount,
            answer.commentCount,
            answer.date
        )
    }
}
