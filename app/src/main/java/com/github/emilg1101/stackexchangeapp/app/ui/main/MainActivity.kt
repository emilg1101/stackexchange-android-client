package com.github.emilg1101.stackexchangeapp.app.ui.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.github.emilg1101.stackexchangeapp.R
import com.github.emilg1101.stackexchangeapp.app.di.AppInjector
import com.github.emilg1101.stackexchangeapp.core.ui.base.NavigationActivity

class MainActivity : NavigationActivity() {

    lateinit var mainNavigator: MainNavigator

    override val navController: NavController
        get() = findNavController(R.id.my_nav_host_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        AppInjector.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        mainNavigator.navController = navController
    }
}
