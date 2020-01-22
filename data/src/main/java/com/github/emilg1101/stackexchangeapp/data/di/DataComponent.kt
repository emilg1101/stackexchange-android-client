package com.github.emilg1101.stackexchangeapp.data.di

import android.content.Context

interface DataComponent {

    val apiComponent: ApiComponent

    val repositoryComponent: RepositoryComponent

    val context: Context // just for test

    val baseUrl: String

    companion object {
        fun create(context: Context) = DataModule(context)
    }
}

class DataModule internal constructor(context: Context) : DataComponent {

    override val context: Context = context

    override val baseUrl: String = "https://api.stackexchange.com/"

    override val apiComponent: ApiComponent = ApiComponent.create(baseUrl)

    override val repositoryComponent: RepositoryComponent = RepositoryComponent.create(apiComponent)
}
