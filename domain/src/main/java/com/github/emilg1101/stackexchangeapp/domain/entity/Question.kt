package com.github.emilg1101.stackexchangeapp.domain.entity

import java.util.Calendar

class Question(
    val id: Int,
    val owner: User,
    val title: String,
    val answerCount: Int,
    val date: Calendar
) {
    var tags: List<String> = listOf("kotlin", "android", "dagger", "androidx")
    var markdown: String = ""
    var commentCount: Int = 0
}
