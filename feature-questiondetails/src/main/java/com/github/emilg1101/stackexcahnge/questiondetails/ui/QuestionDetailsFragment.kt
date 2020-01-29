package com.github.emilg1101.stackexcahnge.questiondetails.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.webkit.WebViewClientCompat
import com.github.emilg1101.stackexcahnge.questiondetails.R
import com.github.emilg1101.stackexcahnge.questiondetails.adapter.AnswersPagingAdapter
import com.github.emilg1101.stackexcahnge.questiondetails.databinding.FragmentQuestionDetailsBinding
import com.github.emilg1101.stackexcahnge.questiondetails.di.QuestionDetailsComponentProvider
import com.github.emilg1101.stackexcahnge.questiondetails.paging.LivePagedListFactory
import com.github.emilg1101.stackexchangeapp.core.ui.base.BaseFragment
import com.github.emilg1101.stackexchangeapp.core.ui.base.AppActivity
import com.github.emilg1101.stackexchangeapp.domain.repository.QuestionsRepository
import com.github.rjeschke.txtmark.Processor
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_question_details.*

class QuestionDetailsFragment : BaseFragment<FragmentQuestionDetailsBinding>(R.layout.fragment_question_details) {

    private val questionId get() = arguments?.getInt("QUESTION_ID") ?: 0

    override val viewModel: QuestionDetailsViewModel by viewModels {
        QuestionDetailsComponentProvider.provideViewModelFactory(questionId)
    }

    private val adapter = AnswersPagingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding.viewmodel = viewModel

        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppActivity)?.setToolbar(toolbar)
        answers_list.layoutManager = LinearLayoutManager(requireContext())
        answers_list.adapter = adapter

        viewModel.questionDetails.observe(viewLifecycleOwner) { model ->
            (activity as? AppCompatActivity)?.supportActionBar?.title = model.title

            body.loadData(Processor.process(model.markdown), "text/html", "UTF-8")
            body.webViewClient = WebViewClientCompat()

            model.tags.forEach {
                tags_group.addView(Chip(context).apply {
                    text = it
                })
            }

            text_answer_count.text = "${model.answerCount} Answers"
        }

        button_question_comments.setOnClickListener { viewModel.openQuestionComments() }

        viewModel.pagedListLiveData.observe(viewLifecycleOwner) { answers ->
            adapter.submitList(answers)
        }
    }
}

class QuestionDetailsViewModelFactory(
    private val questionId: Int,
    private val questionsRepository: QuestionsRepository,
    private val navigation: QuestionDetailsNavigation,
    private val livePagedListFactory: LivePagedListFactory
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        QuestionDetailsViewModel(questionId, questionsRepository, navigation, livePagedListFactory) as T
}
