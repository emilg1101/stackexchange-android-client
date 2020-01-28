package com.github.emilg1101.stackexchangeapp.app.ui.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.github.emilg1101.stackexchangeapp.R
import com.github.emilg1101.stackexchangeapp.app.di.AppInjector.mainComponent
import com.github.emilg1101.stackexchangeapp.app.di.inject
import com.github.emilg1101.stackexchangeapp.core.ui.base.BottomAppActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BottomAppActivity() {

    private val mainNavigator: MainNavigator by inject { mainComponent.navigator }

    override val navGraphIds: List<Int> = listOf(R.navigation.questions_flow, R.navigation.notifications_flow, R.navigation.account_flow)

    override val containerId: Int = R.id.my_nav_host_fragment

    override val bottomNavigationView: BottomNavigationView
        get() = findViewById(R.id.bottom_nav)

    override fun onCreate(savedInstanceState: Bundle?) {
        // AppInjector.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNavigationBar()
    }

    override fun onNavController(navController: NavController) {
        mainNavigator.navController = navController
        setupActionBarWithNavController(navController)
    }

    override fun onResume() {
        super.onResume()
        mainNavigator.navController = navController
    }

    override fun onBackPressed() {
        if (navController?.navigateUp() == false) super.onBackPressed()
    }
}
