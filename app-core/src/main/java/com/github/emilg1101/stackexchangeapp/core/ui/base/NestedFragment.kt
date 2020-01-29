package com.github.emilg1101.stackexchangeapp.core.ui.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding

abstract class NestedFragment<Binding : ViewDataBinding>(containerId: Int) : BaseFragment<Binding>(containerId) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? BottomAppActivity)?.hideBottomNavigation()
    }

    override fun onDetach() {
        super.onDetach()
        (activity as? BottomAppActivity)?.showBottomNavigation()
    }
}
