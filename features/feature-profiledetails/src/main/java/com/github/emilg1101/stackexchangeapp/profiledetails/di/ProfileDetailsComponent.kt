package com.github.emilg1101.stackexchangeapp.profiledetails.di

import com.github.emilg1101.stackexchangeapp.profiledetails.ui.ProfileDetailsViewModelFactory

val ProfileDetailsComponentProvider = ProfileDetailsComponent.instance

abstract class ProfileDetailsComponent {

    fun provideProfileDetailsViewModelFactory() =
        ProfileDetailsViewModelFactory()

    companion object {
        lateinit var instance: ProfileDetailsComponent
    }
}
