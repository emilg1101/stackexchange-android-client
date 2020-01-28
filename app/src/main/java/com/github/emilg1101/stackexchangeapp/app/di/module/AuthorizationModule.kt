package com.github.emilg1101.stackexchangeapp.app.di.module

import com.github.emilg1101.stackexchangeapp.app.ui.main.MainComponent
import com.github.emilg1101.stackexchangeapp.authorization.di.AuthorizationComponent
import com.github.emilg1101.stackexchangeapp.authorization.ui.AuthorizationNavigation

class AuthorizationModule(mainComponent: MainComponent) : AuthorizationComponent() {

    override val navigation: AuthorizationNavigation = mainComponent.navigator
}
