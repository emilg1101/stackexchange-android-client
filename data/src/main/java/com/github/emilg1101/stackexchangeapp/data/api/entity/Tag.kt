package com.github.emilg1101.stackexchangeapp.data.api.entity

import com.google.gson.annotations.SerializedName

data class Tag(
    @field:SerializedName("name")
    val name: String
)

val TagsResultEntityMapper: suspend ResponseWrapper<Tag>.() -> List<String> = {
    items.map { tag -> tag.name }
}
