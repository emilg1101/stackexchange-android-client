package com.github.emilg1101.stackexchangeapp.questionssearch.model

data class TagItemModel(val title: String, var selected: Boolean = false)

val TagItemModelsMapper: suspend (List<String>) -> List<TagItemModel> = {
    it.map { tag -> TagItemModel(tag) }
}
