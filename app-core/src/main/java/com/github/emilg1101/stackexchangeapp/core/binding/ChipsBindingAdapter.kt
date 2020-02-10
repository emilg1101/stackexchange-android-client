package com.github.emilg1101.stackexchangeapp.core.binding

import androidx.databinding.BindingAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

@BindingAdapter("app:chipItems")
fun setChipItems(group: ChipGroup, items: List<String>?) {
    group.removeAllViews()
    items?.forEach {
        group.addView(Chip(group.context).apply {
            text = it
        })
    }
}
