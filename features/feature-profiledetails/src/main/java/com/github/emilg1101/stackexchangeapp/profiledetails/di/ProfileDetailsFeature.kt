package com.github.emilg1101.stackexchangeapp.profiledetails.di

import com.github.emilg1101.stackexchangeapp.core.feature.Feature
import com.github.emilg1101.stackexchangeapp.domain.usecase.users.GetUserUseCase

val profileDetailsFeature: ProfileDetailsFeature = ProfileDetailsFeatureImpl()

interface ProfileDetailsFeature : Feature<ProfileDetailsFeature.Dependencies> {

    interface Dependencies {
        val getUserUseCase: GetUserUseCase
    }
}

internal lateinit var profileDetailsComponent: ProfileDetailsComponent
    private set

private class ProfileDetailsFeatureImpl internal constructor() : ProfileDetailsFeature {

    override fun inject(dependencies: ProfileDetailsFeature.Dependencies) {
        profileDetailsComponent = ProfileDetailsComponent.create(dependencies)
    }
}
