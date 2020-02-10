package com.github.emilg1101.stackexchangeapp.core.util

import androidx.lifecycle.LiveData

class ListLiveData<T>(value: List<T>) : LiveData<List<T>>(value) {

    operator fun plus(item: T) = value?.let {
        val list = it.toMutableList()
        list.add(item)
        value = list
    }

    operator fun minus(item: T) = value?.let {
        val list = it.toMutableList()
        list.remove(item)
        value = list
    }
}
