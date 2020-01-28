package com.github.emilg1101.stackexchangeapp.notifications.di

import com.github.emilg1101.stackexchangeapp.notifications.ui.NotificationsViewModelFactory

val NotificationsComponentProvider = NotificationsComponent.instance

abstract class NotificationsComponent {

    fun provideNotificationsViewModelFactory() = NotificationsViewModelFactory()

    companion object {
        lateinit var instance: NotificationsComponent
    }
}
