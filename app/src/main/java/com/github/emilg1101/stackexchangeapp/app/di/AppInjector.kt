package com.github.emilg1101.stackexchangeapp.app.di

import android.app.Application
import com.github.emilg1101.stackexcahnge.questiondetails.di.QuestionDetailsComponent
import com.github.emilg1101.stackexchangeapp.app.di.module.QuestionDetailsModule
import com.github.emilg1101.stackexchangeapp.app.di.module.QuestionsSearchModule
import com.github.emilg1101.stackexchangeapp.app.ui.main.MainActivity
import com.github.emilg1101.stackexchangeapp.app.ui.main.MainComponent
import com.github.emilg1101.stackexchangeapp.data.di.DataComponent
import com.github.emilg1101.stackexchangeapp.questionssearch.di.QuestionsSearchComponent

object AppInjector {

    private lateinit var appComponent: AppComponent
    private lateinit var dataComponent: DataComponent
    private lateinit var mainComponent: MainComponent

    fun init(application: Application) {
        appComponent = AppComponent.create(application)
        dataComponent = DataComponent.create(appComponent.context)
        mainComponent = MainComponent.create()
        QuestionsSearchComponent.instance =
            QuestionsSearchModule(appComponent, dataComponent, mainComponent)
        QuestionDetailsComponent.instance = QuestionDetailsModule(dataComponent)
    }

    fun inject(activity: MainActivity) {
        activity.mainNavigator = mainComponent.navigator
    }
}
