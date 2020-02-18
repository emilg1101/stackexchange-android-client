package com.github.emilg1101.stackexchangeapp.profiledetails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.github.emilg1101.stackexchangeapp.core.extensions.doOnApplyWindowInsets
import com.github.emilg1101.stackexchangeapp.core.ui.base.NestedFragment
import com.github.emilg1101.stackexchangeapp.profiledetails.R
import com.github.emilg1101.stackexchangeapp.profiledetails.databinding.FragmentProfileDetailsBinding
import com.github.emilg1101.stackexchangeapp.profiledetails.di.profileDetailsComponent
import kotlinx.android.synthetic.main.fragment_profile_details.background

class ProfileDetailsFragment : NestedFragment<FragmentProfileDetailsBinding>(R.layout.fragment_profile_details) {

    private val userId get() = arguments?.getInt("USER_ID") ?: 0

    override val viewModel: ProfileDetailsViewModel by viewModels { viewModelFactory }

    override val toolbar: Toolbar?
        get() = view?.findViewById(R.id.toolbar)

    override fun onCreate(savedInstanceState: Bundle?) {
        profileDetailsComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.doOnApplyWindowInsets { v, windowInsetsCompat, _ ->
            binding.motionLayout.minimumHeight = v.measuredHeight + windowInsetsCompat.systemWindowInsetTop
        }
        viewModel.setUserId(userId)
        viewModel.userDetails.observe(this) { model ->
            toolbar?.title = model.name
            Glide.with(requireActivity()).load(model.image).into(background)
        }
    }
}
