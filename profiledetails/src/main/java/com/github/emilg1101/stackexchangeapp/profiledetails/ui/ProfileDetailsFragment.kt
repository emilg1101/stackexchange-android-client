package com.github.emilg1101.stackexchangeapp.profiledetails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.emilg1101.stackexchangeapp.core.ui.base.BaseFragment
import com.github.emilg1101.stackexchangeapp.profiledetails.R
import com.github.emilg1101.stackexchangeapp.profiledetails.databinding.FragmentProfileDetailsBinding
import com.github.emilg1101.stackexchangeapp.profiledetails.di.ProfileDetailsComponentProvider
import kotlinx.android.synthetic.main.fragment_profile_details.*

class ProfileDetailsFragment : BaseFragment<FragmentProfileDetailsBinding>(R.layout.fragment_profile_details) {

    override val viewModel: ProfileDetailsViewModel by viewModels {
        ProfileDetailsComponentProvider.provideProfileDetailsViewModelFactory()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        // binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar(toolbar)
    }
}

class ProfileDetailsViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        ProfileDetailsViewModel() as T
}
