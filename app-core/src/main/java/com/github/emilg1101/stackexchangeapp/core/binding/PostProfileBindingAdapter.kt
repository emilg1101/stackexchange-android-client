package com.github.emilg1101.stackexchangeapp.core.binding

import androidx.databinding.BindingAdapter
import com.github.emilg1101.stackexchangeapp.core.ui.widget.PostProfileView
import java.util.Calendar

@BindingAdapter("app:postDate")
fun loadProfileImage(view: PostProfileView, date: Calendar?) {
    view.postDate = date
}
