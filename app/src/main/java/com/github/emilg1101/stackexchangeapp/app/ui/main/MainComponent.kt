package com.github.emilg1101.stackexchangeapp.app.ui.main

interface MainComponent {

    val navigator: MainNavigator

    fun provideMainViewModelFactory(): MainViewModelFactory

    companion object Factory {
        fun create() = MainModule()
    }
}

class MainModule internal constructor() : MainComponent {

    override fun provideMainViewModelFactory() = MainViewModelFactory()

    override val navigator: MainNavigator = MainNavigator()
}
