package com.github.emilg1101.stackexchangeapp.domain.entity

import java.util.Calendar

class Comment(
    val id: Int,
    val body: String,
    val owner: Owner,
    val creationDate: Calendar
)
