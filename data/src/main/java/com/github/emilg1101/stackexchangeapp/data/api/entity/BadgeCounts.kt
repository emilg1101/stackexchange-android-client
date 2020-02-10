package com.github.emilg1101.stackexchangeapp.data.api.entity

import com.google.gson.annotations.SerializedName

data class BadgeCounts(
    @field:SerializedName("bronze")
    val bronze: Int = 0,
    @field:SerializedName("silver")
    val silver: Int = 0,
    @field:SerializedName("gold")
    val gold: Int = 0
)
