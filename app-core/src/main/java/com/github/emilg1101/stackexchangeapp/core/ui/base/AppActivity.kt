package com.github.emilg1101.stackexchangeapp.core.ui.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController

abstract class AppActivity : AppCompatActivity(), NavigationHost {

    var navController: NavController? = null

    override fun registerToolbarWithNavigation(toolbar: Toolbar) {
        toolbar.setNavigationOnClickListener { navController?.popBackStack() }
        navController?.let { toolbar.setupWithNavController(it) }
    }
}
