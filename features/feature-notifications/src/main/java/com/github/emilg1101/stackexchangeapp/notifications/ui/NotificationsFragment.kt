package com.github.emilg1101.stackexchangeapp.notifications.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.emilg1101.stackexchangeapp.core.ui.base.BaseFragment
import com.github.emilg1101.stackexchangeapp.notifications.R
import com.github.emilg1101.stackexchangeapp.notifications.databinding.FragmentNotificationsBinding
import com.github.emilg1101.stackexchangeapp.notifications.di.NotificationsComponentProvider

class NotificationsFragment : BaseFragment<FragmentNotificationsBinding>(R.layout.fragment_notifications) {

    override val viewModel: NotificationsViewModel by viewModels {
        NotificationsComponentProvider.provideNotificationsViewModelFactory()
    }

    override val toolbar: Toolbar?
        get() = view?.findViewById(R.id.toolbar)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }
}

class NotificationsViewModelFactory internal constructor() : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        NotificationsViewModel() as T
}
