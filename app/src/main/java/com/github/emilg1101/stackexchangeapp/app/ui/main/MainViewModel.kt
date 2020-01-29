package com.github.emilg1101.stackexchangeapp.app.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class MainViewModel : ViewModel() {

    var currentNavController: LiveData<NavController>? = null
}
