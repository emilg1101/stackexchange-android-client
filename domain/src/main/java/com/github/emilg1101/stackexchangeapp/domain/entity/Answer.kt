package com.github.emilg1101.stackexchangeapp.domain.entity

import java.util.Calendar

class Answer(
    val id: Int,
    val questionId: Int,
    val owner: User,
    val accepted: Boolean,
    val commentCount: Int,
    val upVoteCount: Int,
    val downVoteCount: Int,
    val date: Calendar
) {
    var body: String = ""
}
