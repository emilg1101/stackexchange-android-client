package com.github.emilg1101.stackexchangeapp.comments.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.emilg1101.stackexchangeapp.comments.R
import com.github.emilg1101.stackexchangeapp.comments.databinding.FragmentCommentsBinding
import com.github.emilg1101.stackexchangeapp.comments.di.CommentsComponentProvider
import com.github.emilg1101.stackexchangeapp.core.ui.base.NestedFragment
import kotlinx.android.synthetic.main.fragment_comments.*

class CommentsFragment : NestedFragment<FragmentCommentsBinding>(R.layout.fragment_comments) {

    override val viewModel: CommentsViewModel by viewModels {
        CommentsComponentProvider.provideCommentsViewModelFactory()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar(toolbar)
    }
}

class CommentsViewModelFactory internal constructor() : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        CommentsViewModel() as T
}
