package com.github.emilg1101.stackexchangeapp.core.ui.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController

abstract class AppActivity : AppCompatActivity() {

    var navController: NavController? = null

    fun setToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { navController?.popBackStack() }
        navController?.let { setupActionBarWithNavController(it) }
    }
}
