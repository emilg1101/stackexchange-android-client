package com.github.emilg1101.stackexchangeapp.core.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData

@BindingAdapter("app:goneUnless")
fun goneUnless(view: View, visible: LiveData<Boolean>) {
    goneUnless(view, visible.value == true)
}

@BindingAdapter("app:goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("app:invisibleUnless")
fun invisibleUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
}
