package com.github.emilg1101.stackexchangeapp.core.ui.base

import android.content.Context
import androidx.databinding.ViewDataBinding

abstract class NestedFragment<Binding : ViewDataBinding>(containerId: Int) : BaseFragment<Binding>(containerId) {

    override fun onAttach(context: Context) {
        (activity as? BottomAppActivity)?.hideBottomNavigation()
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
        (activity as? BottomAppActivity)?.showBottomNavigation()
    }
}
