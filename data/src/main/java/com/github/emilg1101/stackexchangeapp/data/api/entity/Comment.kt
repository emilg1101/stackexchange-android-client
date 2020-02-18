package com.github.emilg1101.stackexchangeapp.data.api.entity

import com.github.emilg1101.stackexchangeapp.data.extensions.toCalendar
import com.github.emilg1101.stackexchangeapp.domain.entity.Comment
import com.google.gson.annotations.SerializedName

typealias ApiComment = com.github.emilg1101.stackexchangeapp.data.api.entity.Comment

data class Comment(
    @field:SerializedName("comment_id")
    val id: Int,
    @field:SerializedName("body")
    val body: String,
    @field:SerializedName("owner")
    val owner: User,
    @field:SerializedName("creation_date")
    val creationDate: Long
)

val CommentEntityMapper: ApiComment.() -> Comment = {
    Comment(
        id,
        body,
        UserEntityMapper(owner),
        creationDate.toCalendar()
    )
}

val CommentsResultEntityMapper: suspend ResponseWrapper<ApiComment>.() -> List<Comment> = {
    items.map(CommentEntityMapper)
}
