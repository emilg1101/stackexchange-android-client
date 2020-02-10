package com.github.emilg1101.stackexchangeapp.data.api.entity

import com.google.gson.annotations.SerializedName

class ResponseWrapper<Entity> {

    @field:SerializedName("items")
    val items: List<Entity> = emptyList()

    @field:SerializedName("error_id")
    val errorId: Int? = null

    @field:SerializedName("error_message")
    val errorMessage: String? = null

    @field:SerializedName("error_name")
    val errorName: String? = null
}
