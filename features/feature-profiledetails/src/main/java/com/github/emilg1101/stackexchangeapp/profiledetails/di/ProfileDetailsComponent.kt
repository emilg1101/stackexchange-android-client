package com.github.emilg1101.stackexchangeapp.profiledetails.di

import com.github.emilg1101.stackexchangeapp.domain.usecase.users.GetUserUseCase
import com.github.emilg1101.stackexchangeapp.profiledetails.ui.ProfileDetailsFragment
import com.github.emilg1101.stackexchangeapp.profiledetails.ui.ProfileDetailsViewModelFactory

internal interface ProfileDetailsComponent {

    fun inject(fragment: ProfileDetailsFragment)

    companion object {
        fun create(dependencies: ProfileDetailsFeature.Dependencies): ProfileDetailsComponent =
            ProfileDetailsModule(dependencies.getUserUseCase)
    }
}

private class ProfileDetailsModule internal constructor(getUserUseCase: GetUserUseCase) : ProfileDetailsComponent {

    val viewModelFactory: ProfileDetailsViewModelFactory = ProfileDetailsViewModelFactory(getUserUseCase)

    override fun inject(fragment: ProfileDetailsFragment) {
        fragment.viewModelFactory = viewModelFactory
    }
}
