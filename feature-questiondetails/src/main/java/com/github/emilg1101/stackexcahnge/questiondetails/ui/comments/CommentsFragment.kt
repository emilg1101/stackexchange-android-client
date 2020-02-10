package com.github.emilg1101.stackexcahnge.questiondetails.ui.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.emilg1101.stackexcahnge.questiondetails.R
import com.github.emilg1101.stackexcahnge.questiondetails.adapter.CommentsPagingAdapter
import com.github.emilg1101.stackexcahnge.questiondetails.databinding.FragmentCommentsBinding
import com.github.emilg1101.stackexcahnge.questiondetails.di.commentsComponent
import com.github.emilg1101.stackexcahnge.questiondetails.paging.CommentsLivePagedListFactory
import com.github.emilg1101.stackexchangeapp.core.ui.base.NestedFragment
import kotlinx.android.synthetic.main.fragment_comments.*

class CommentsFragment : NestedFragment<FragmentCommentsBinding>(R.layout.fragment_comments) {

    private val postId get() = arguments?.getInt("POST_ID") ?: 0

    override val viewModel: CommentsViewModel by viewModels {
        commentsComponent.provideCommentsViewModelFactory(postId)
    }

    private val adapter = CommentsPagingAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar(toolbar)

        comment_list.layoutManager = LinearLayoutManager(context)
        comment_list.adapter = adapter

        viewModel.pagedListLiveData.observe(viewLifecycleOwner) { comments ->
            adapter.submitList(comments)
        }
    }
}

class CommentsViewModelFactory internal constructor(
    private val postId: Int,
    private val provideLivePagedListFactory: CommentsLivePagedListFactory
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        CommentsViewModel(postId, provideLivePagedListFactory) as T
}
