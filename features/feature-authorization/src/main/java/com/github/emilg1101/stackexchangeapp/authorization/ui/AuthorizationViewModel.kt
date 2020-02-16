package com.github.emilg1101.stackexchangeapp.authorization.ui

import com.github.emilg1101.stackexchangeapp.core.ui.base.BaseViewModel

class AuthorizationViewModel internal constructor(
    private val navigation: AuthorizationNavigation
) : BaseViewModel() {

    fun openProfileDetails() {
        navigation.openProfileDetails()
    }
}
