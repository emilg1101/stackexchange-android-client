package com.github.emilg1101.stackexchangeapp.core.ui.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController

abstract class NavigationActivity : AppCompatActivity() {

    abstract val navController: NavController

    fun setToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController)
        toolbar.setNavigationOnClickListener { navController.popBackStack() }
    }
}
