package com.github.emilg1101.stackexcahnge.questiondetails.model

import com.github.emilg1101.stackexchangeapp.domain.entity.Comment
import java.util.Calendar

data class CommentItemModel(
    val id: Int,
    val ownerImage: String,
    val ownerName: String,
    val body: String,
    val date: Calendar
)

val CommentItemModelMapper: suspend (List<Comment>) -> List<CommentItemModel> = {
    it.map { comment ->
        CommentItemModel(
            comment.id,
            comment.owner.profileImage,
            comment.owner.name,
            comment.body,
            comment.creationDate
        )
    }
}
