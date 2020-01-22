package com.github.emilg1101.stackexchangeapp.app.ui.main

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.github.emilg1101.stackexchangeapp.R
import com.github.emilg1101.stackexchangeapp.questionssearch.ui.QuestionsSearchNavigation

class MainNavigator : QuestionsSearchNavigation {

    var navController: NavController? = null

    override fun openQuestionDetails(questionId: Int) {
        navController?.navigate(R.id.action_questionsSearch_to_questionDetails, bundleOf("QUESTION_ID" to questionId))
    }
}