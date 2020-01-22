package com.github.emilg1101.stackexchangeapp.questionssearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.emilg1101.stackexchangeapp.core.ui.base.BaseFragment
import com.github.emilg1101.stackexchangeapp.core.ui.base.NavigationActivity
import com.github.emilg1101.stackexchangeapp.domain.repository.QuestionsRepository
import com.github.emilg1101.stackexchangeapp.domain.repository.TagsRepository
import com.github.emilg1101.stackexchangeapp.questionssearch.R
import com.github.emilg1101.stackexchangeapp.questionssearch.adapter.QuestionsPagingAdapter
import com.github.emilg1101.stackexchangeapp.questionssearch.adapter.SortSpinnerAdapter
import com.github.emilg1101.stackexchangeapp.questionssearch.databinding.FragmentQuestionsSearchBinding
import com.github.emilg1101.stackexchangeapp.questionssearch.di.QuestionsSearchComponentProvider
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_questions_search.*

class QuestionsSearchFragment :
    BaseFragment<FragmentQuestionsSearchBinding>(R.layout.fragment_questions_search) {

    private val component = QuestionsSearchComponentProvider

    private val adapter = QuestionsPagingAdapter().apply {
        onQuestionClick = {
            viewModel.openQuestion(it)
        }
    }

    private var sortAdapter: SortSpinnerAdapter? = null

    private val onItemSelected = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            if (sortAdapter == null) {
                viewModel.changeSort(Sort(parent?.adapter?.getItem(position).toString().toLowerCase()))
            }
            initAdapter()
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }
    }

    override val viewModel: QuestionsSearchViewModel by viewModels {
        component.provideViewModelFactory()
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

        if (sortAdapter == null) {
            sortAdapter = SortSpinnerAdapter(requireContext(), resources.getStringArray(R.array.sort_types))
        }

        spinner.adapter = sortAdapter
        spinner.onItemSelectedListener = onItemSelected

        (activity as? NavigationActivity)?.setToolbar(toolbar)
        (activity as? NavigationActivity)?.supportActionBar?.setDisplayShowTitleEnabled(false)

        viewModel.tagsLiveData.observe(viewLifecycleOwner) { tags ->
            tags.map {
                (layoutInflater.inflate(R.layout.chip_default, tags_group, false) as? Chip)?.apply {
                    text = it.title
                    isChecked = it.selected
                    setOnCheckedChangeListener { buttonView, isChecked ->
                        viewModel.addTag(it.title)
                        initAdapter()
                    }
                }
            }.forEach { tags_group.addView(it) }
        }

        list.layoutManager = LinearLayoutManager(requireContext())
        list.adapter = adapter
    }

    private fun initAdapter() {
        viewModel.pagedListLiveData.observe(viewLifecycleOwner) { questions ->
            adapter.submitList(questions)
        }
    }
}

class QuestionsSearchViewModelFactory(
    private val questionsRepository: QuestionsRepository,
    private val tagsRepository: TagsRepository,
    private val questionsSearchNavigation: QuestionsSearchNavigation
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        QuestionsSearchViewModel(
            questionsRepository,
            tagsRepository,
            questionsSearchNavigation
        ) as T
}

inline class Sort(val sort: String)

val HotSort = Sort("hot")