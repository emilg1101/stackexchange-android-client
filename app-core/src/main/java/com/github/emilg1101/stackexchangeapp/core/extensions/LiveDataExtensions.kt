package com.github.emilg1101.stackexchangeapp.core.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.emilg1101.stackexchangeapp.core.util.DoubleLiveData

fun <A, B> LiveData<A>.combine(another: LiveData<B>) = DoubleLiveData(this, another)

fun <T> MutableLiveData<T>.setValueIfNew(newValue: T) {
    if (this.value != newValue) value = newValue
}
