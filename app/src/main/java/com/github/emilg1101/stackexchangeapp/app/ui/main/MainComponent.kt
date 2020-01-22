package com.github.emilg1101.stackexchangeapp.app.ui.main

interface MainComponent {

    val navigator: MainNavigator

    companion object Factory {
        fun create() = MainModule()
    }
}

class MainModule internal constructor() : MainComponent {

    override val navigator: MainNavigator = MainNavigator()
}
