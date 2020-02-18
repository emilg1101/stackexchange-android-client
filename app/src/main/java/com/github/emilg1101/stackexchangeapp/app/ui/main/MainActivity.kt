package com.github.emilg1101.stackexchangeapp.app.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.github.emilg1101.stackexchangeapp.R
import com.github.emilg1101.stackexchangeapp.app.di.AppInjector.mainComponent
import com.github.emilg1101.stackexchangeapp.app.di.inject
import com.github.emilg1101.stackexchangeapp.core.ui.base.BaseFragment
import com.github.emilg1101.stackexchangeapp.core.ui.base.BottomAppActivity
import com.github.emilg1101.stackexchangeapp.core.ui.util.NoopWindowInsetsListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.content

class MainActivity : BottomAppActivity() {

    private val mainNavigator: MainNavigator by inject { mainComponent.navigator }

    private val viewModel: MainViewModel by viewModels { mainComponent.provideMainViewModelFactory() }

    override val navGraphIds: List<Int> = listOf(R.navigation.questions_flow, R.navigation.notifications_flow, R.navigation.account_flow)

    override val containerId: Int = R.id.my_nav_host_fragment

    override val bottomNavigationView: BottomNavigationView?
        get() = findViewById(R.id.bottom_nav)

    private val currentFragment: BaseFragment<*>?
        get() = supportFragmentManager.findFragmentById(containerId)?.childFragmentManager?.fragments?.get(0) as? BaseFragment<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }

        content.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        content.setOnApplyWindowInsetsListener(NoopWindowInsetsListener)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    override fun onNavController(navController: NavController) {
        mainNavigator.navController = navController
        currentFragment?.toolbar?.let { registerToolbarWithNavigation(it) }
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
