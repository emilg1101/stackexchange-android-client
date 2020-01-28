package com.github.emilg1101.stackexchangeapp.authorization.di

import com.github.emilg1101.stackexchangeapp.authorization.ui.AuthorizationNavigation
import com.github.emilg1101.stackexchangeapp.authorization.ui.AuthorizationViewModelFactory

val AuthorizationComponentProvider = AuthorizationComponent.instance

abstract class AuthorizationComponent {

    abstract val navigation: AuthorizationNavigation

    fun provideAuthorizationViewModelFactory() = AuthorizationViewModelFactory(navigation)

    companion object {
        lateinit var instance: AuthorizationComponent
    }
}
