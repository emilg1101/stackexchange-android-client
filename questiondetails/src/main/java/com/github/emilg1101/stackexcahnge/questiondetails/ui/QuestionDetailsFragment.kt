package com.github.emilg1101.stackexcahnge.questiondetails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.github.emilg1101.stackexcahnge.questiondetails.R
import com.github.emilg1101.stackexcahnge.questiondetails.databinding.FragmentQuestionDetailsBinding
import com.github.emilg1101.stackexcahnge.questiondetails.di.QuestionDetailsComponentProvider
import com.github.emilg1101.stackexchangeapp.core.ui.base.BaseFragment
import com.github.emilg1101.stackexchangeapp.core.ui.base.NavigationActivity
import com.github.emilg1101.stackexchangeapp.domain.repository.QuestionsRepository
import kotlinx.android.synthetic.main.fragment_question_details.*

class QuestionDetailsFragment : BaseFragment<FragmentQuestionDetailsBinding>(R.layout.fragment_question_details) {

    private val questionId get() = arguments?.getInt("QUESTION_ID") ?: 0

    override val viewModel: QuestionDetailsViewModel by viewModels {
        QuestionDetailsComponentProvider.provideViewModelFactory(questionId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding.viewmodel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? NavigationActivity)?.setToolbar(toolbar)

        viewModel.questionDetails.observe(viewLifecycleOwner) { model ->
            (activity as? AppCompatActivity)?.supportActionBar?.title = model.title
        }
    }
}

class QuestionDetailsViewModelFactory(
    private val questionId: Int,
    private val questionsRepository: QuestionsRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        QuestionDetailsViewModel(questionId, questionsRepository) as T
}
