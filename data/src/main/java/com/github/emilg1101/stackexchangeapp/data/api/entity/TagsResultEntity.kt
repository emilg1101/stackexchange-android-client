package com.github.emilg1101.stackexchangeapp.data.api.entity

import com.google.gson.annotations.SerializedName

class TagsResultEntity {

    @field:SerializedName("items")
    val items: List<TagEntity> = listOf()
}

val TagsResultEntityMapper: suspend (TagsResultEntity) -> List<String> = {
    it.items.map { tag -> tag.name }
}
