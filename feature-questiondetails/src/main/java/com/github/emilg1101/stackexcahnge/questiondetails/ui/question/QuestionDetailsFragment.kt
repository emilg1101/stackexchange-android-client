package com.github.emilg1101.stackexcahnge.questiondetails.ui.question

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.emilg1101.stackexcahnge.questiondetails.R
import com.github.emilg1101.stackexcahnge.questiondetails.adapter.AnswersPagingAdapter
import com.github.emilg1101.stackexcahnge.questiondetails.databinding.FragmentQuestionDetailsBinding
import com.github.emilg1101.stackexcahnge.questiondetails.di.questionDetailsComponent
import com.github.emilg1101.stackexchangeapp.core.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_question_details.answers_list
import kotlinx.android.synthetic.main.fragment_question_details.scroll
import kotlinx.android.synthetic.main.fragment_question_details.text_title
import kotlinx.android.synthetic.main.fragment_question_details.text_toolbar_title
import kotlinx.android.synthetic.main.fragment_question_details.toolbar

class QuestionDetailsFragment : BaseFragment<FragmentQuestionDetailsBinding>(R.layout.fragment_question_details) {

    private val questionId get() = arguments?.getInt("QUESTION_ID") ?: 0

    override val viewModel: QuestionDetailsViewModel by viewModels { viewModelFactory }

    lateinit var adapter: AnswersPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        questionDetailsComponent.inject(this)
        super.onCreate(savedInstanceState)
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

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setQuestionId(questionId)
        setToolbar(toolbar)
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayShowTitleEnabled(false)

        answers_list.layoutManager = LinearLayoutManager(requireContext())
        answers_list.adapter = adapter

        adapter.onCommentClick = {
            viewModel.openAnswerComments(it.answerId)
        }
        adapter.onAnswerClick = {
            viewModel.openAnswerDetails(it.answerId)
        }

        binding.model = viewModel.questionDetails

        viewModel.pagedListLiveData.observe(viewLifecycleOwner) { answers ->
            adapter.submitList(answers)
        }
    }

    override fun onLayout(view: View, savedInstanceState: Bundle?) {
        super.onLayout(view, savedInstanceState)

        val toolbarLocation = IntArray(2)
        toolbar.getLocationInWindow(toolbarLocation)

        val toolbarTitleLocation = IntArray(2)
        text_toolbar_title.getLocationInWindow(toolbarTitleLocation)
        val toolbarTitleY = toolbarTitleLocation[1] - toolbarLocation[1]

        if (savedInstanceState == null) {
            text_toolbar_title.y = 200f
        } else {
            text_toolbar_title.requestLayout()
        }

        scroll.setOnScrollChangeListener { _: NestedScrollView?, _: Int, _: Int, _: Int, _: Int ->
            val location = IntArray(2)
            text_title.getLocationInWindow(location)
            val titleY = location[1] - toolbarLocation[1]
            if (titleY > toolbarTitleY) {
                text_toolbar_title.y = titleY.toFloat()
            } else {
                text_toolbar_title.y = toolbarTitleY.toFloat()
            }
        }
    }
}
