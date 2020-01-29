package com.github.emilg1101.stackexchangeapp.comments.di

import com.github.emilg1101.stackexchangeapp.comments.ui.CommentsViewModelFactory

val CommentsComponentProvider = CommentsComponent.instance

abstract class CommentsComponent {

    fun provideCommentsViewModelFactory() = CommentsViewModelFactory()

    companion object {
        lateinit var instance: CommentsComponent
    }
}
