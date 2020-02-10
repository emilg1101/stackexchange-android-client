package com.github.emilg1101.stackexchangeapp.core.binding

import androidx.databinding.BindingAdapter
import com.github.emilg1101.stackexchangeapp.core.ui.widget.MarkdownTextView

@BindingAdapter("app:markdown")
fun loadMarkdown(textView: MarkdownTextView, markdown: String?) {
    textView.markdown = markdown ?: ""
}
