package com.github.emilg1101.stackexchangeapp.domain.entity

class Question(
    val id: Int,
    val owner: Owner,
    val title: String
) {
    var tags: List<String> = listOf("kotlin", "android", "dagger", "androidx")
    var markdown: String = ""
}
