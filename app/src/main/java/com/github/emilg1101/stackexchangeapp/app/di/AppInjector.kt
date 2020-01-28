package com.github.emilg1101.stackexchangeapp.app.di

import android.app.Application
import com.github.emilg1101.stackexcahnge.questiondetails.di.QuestionDetailsComponent
import com.github.emilg1101.stackexchangeapp.app.di.module.AuthorizationModule
import com.github.emilg1101.stackexchangeapp.app.di.module.NotificationsModule
import com.github.emilg1101.stackexchangeapp.app.di.module.ProfileDetailsModule
import com.github.emilg1101.stackexchangeapp.app.di.module.QuestionDetailsModule
import com.github.emilg1101.stackexchangeapp.app.di.module.QuestionsSearchModule
import com.github.emilg1101.stackexchangeapp.app.ui.main.MainComponent
import com.github.emilg1101.stackexchangeapp.authorization.di.AuthorizationComponent
import com.github.emilg1101.stackexchangeapp.data.di.DataComponent
import com.github.emilg1101.stackexchangeapp.notifications.di.NotificationsComponent
import com.github.emilg1101.stackexchangeapp.profiledetails.di.ProfileDetailsComponent
import com.github.emilg1101.stackexchangeapp.questionssearch.di.QuestionsSearchComponent

object AppInjector {

    private lateinit var appComponent: AppComponent
    private lateinit var dataComponent: DataComponent
    lateinit var mainComponent: MainComponent

    fun init(application: Application) {
        appComponent = AppComponent.create(application)
        dataComponent = DataComponent.create(appComponent.context)
        mainComponent = MainComponent.create()
        QuestionsSearchComponent.instance = QuestionsSearchModule(appComponent, dataComponent, mainComponent)
        QuestionDetailsComponent.instance = QuestionDetailsModule(dataComponent)
        ProfileDetailsComponent.instance = ProfileDetailsModule()
        AuthorizationComponent.instance = AuthorizationModule(mainComponent)
        NotificationsComponent.instance = NotificationsModule()
    }
}

inline fun <reified T : Any> inject(noinline factoryProducer: ((AppInjector) -> T)): Lazy<T> {
    return object : Lazy<T> {
        override val value = factoryProducer(AppInjector)

        override fun isInitialized() = true
    }
}
