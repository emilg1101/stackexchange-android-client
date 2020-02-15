package com.github.emilg1101.stackexchangeapp.core.binding

import androidx.databinding.BindingAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

@BindingAdapter("app:chipItems", "app:clickListener", requireAll = false)
fun setChipItems(group: ChipGroup, items: List<String>?, clickListener: ChipClickListener? = null) {
    group.removeAllViews()
    items?.forEach { item ->
        group.addView(Chip(group.context).apply {
            text = item
            setOnClickListener { clickListener?.click(item) }
        })
    }
}

interface ChipClickListener {
    fun click(text: String)
}
