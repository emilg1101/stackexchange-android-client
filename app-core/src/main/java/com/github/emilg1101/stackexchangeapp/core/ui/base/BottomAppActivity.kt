package com.github.emilg1101.stackexchangeapp.core.ui.base

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.github.emilg1101.stackexchangeapp.core.extensions.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

abstract class BottomAppActivity : AppActivity() {

    abstract val bottomNavigationView: BottomNavigationView?

    abstract val containerId: Int

    abstract val navGraphIds: List<Int>

    private var currentNavController: LiveData<NavController>? = null

    fun showBottomNavigation() {
        bottomNavigationView?.visibility = View.VISIBLE
    }

    fun hideBottomNavigation() {
        bottomNavigationView?.visibility = View.GONE
    }

    fun setupBottomNavigationBar() {

        val controller = bottomNavigationView?.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = containerId,
            intent = intent
        )

        controller?.observe(this, Observer { navController ->
            this.navController = navController
            onNavController(navController)
        })
        currentNavController = controller
    }

    abstract fun onNavController(navController: NavController)
}
