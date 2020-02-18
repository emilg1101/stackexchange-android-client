package com.github.emilg1101.stackexcahnge.questiondetails.ui.answer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.emilg1101.stackexcahnge.questiondetails.R
import com.github.emilg1101.stackexcahnge.questiondetails.adapter.CommentsPagingAdapter
import com.github.emilg1101.stackexcahnge.questiondetails.databinding.FragmentAnswerDetailsBinding
import com.github.emilg1101.stackexcahnge.questiondetails.di.answerDetailsComponent
import com.github.emilg1101.stackexchangeapp.core.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_comments.comment_list

class AnswerDetailsFragment : BaseFragment<FragmentAnswerDetailsBinding>(R.layout.fragment_answer_details) {

    private val answerId get() = arguments?.getInt("ANSWER_ID") ?: 0

    override val viewModel: AnswerDetailsViewModel by viewModels { viewModelFactory }

    lateinit var adapter: CommentsPagingAdapter

    override val toolbar: Toolbar?
        get() = view?.findViewById(R.id.toolbar)

    override fun onCreate(savedInstanceState: Bundle?) {
        answerDetailsComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setAnswerId(answerId)

        comment_list.layoutManager = LinearLayoutManager(context)
        comment_list.adapter = adapter

        binding.model = viewModel.answerDetails

        viewModel.pagedListLiveData.observe(viewLifecycleOwner) { comments ->
            adapter.submitList(comments)
        }
    }
}
