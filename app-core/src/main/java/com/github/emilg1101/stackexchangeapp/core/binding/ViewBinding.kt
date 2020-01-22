package com.github.emilg1101.stackexchangeapp.core.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData

@BindingAdapter("app:goneUnless")
fun goneUnless(view: View, visible: LiveData<Boolean>) {
    view.visibility = if (visible.value == true) View.VISIBLE else View.GONE
}
