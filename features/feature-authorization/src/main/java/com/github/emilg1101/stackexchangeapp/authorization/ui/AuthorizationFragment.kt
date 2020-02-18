package com.github.emilg1101.stackexchangeapp.authorization.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.emilg1101.stackexchangeapp.authorization.R
import com.github.emilg1101.stackexchangeapp.authorization.databinding.FragmentAuthorizationBinding
import com.github.emilg1101.stackexchangeapp.authorization.di.AuthorizationComponentProvider
import com.github.emilg1101.stackexchangeapp.core.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_authorization.*

class AuthorizationFragment : BaseFragment<FragmentAuthorizationBinding>(R.layout.fragment_authorization) {

    override val viewModel: AuthorizationViewModel by viewModels {
        AuthorizationComponentProvider.provideAuthorizationViewModelFactory()
    }

    override val toolbar: Toolbar?
        get() = view?.findViewById(R.id.toolbar)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        // binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // setToolbar(toolbar)
        button_signin.setOnClickListener { viewModel.openProfileDetails() }
    }
}

class AuthorizationViewModelFactory(
    private val navigation: AuthorizationNavigation
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        AuthorizationViewModel(navigation) as T
}
