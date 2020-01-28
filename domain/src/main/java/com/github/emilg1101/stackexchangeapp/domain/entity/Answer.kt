package com.github.emilg1101.stackexchangeapp.domain.entity

class Answer(
    val id: Int,
    val questionId: Int,
    val owner: Owner,
    val accepted: Boolean
) {
    var markdown: String = ""
}
