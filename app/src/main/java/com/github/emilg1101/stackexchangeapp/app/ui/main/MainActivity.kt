package com.github.emilg1101.stackexchangeapp.app.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.github.emilg1101.stackexchangeapp.R
import com.github.emilg1101.stackexchangeapp.app.di.AppInjector.mainComponent
import com.github.emilg1101.stackexchangeapp.app.di.inject
import com.github.emilg1101.stackexchangeapp.core.ui.base.BottomAppActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BottomAppActivity() {

    private val mainNavigator: MainNavigator by inject { mainComponent.navigator }

    private val viewModel: MainViewModel by viewModels { mainComponent.provideMainViewModelFactory() }

    override val navGraphIds: List<Int> = listOf(R.navigation.questions_flow, R.navigation.notifications_flow, R.navigation.account_flow)

    override val containerId: Int = R.id.my_nav_host_fragment

    override val bottomNavigationView: BottomNavigationView?
        get() = findViewById(R.id.bottom_nav)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
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

class MainViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        MainViewModel() as T
}
