package com.github.emilg1101.stackexchangeapp.questions.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.emilg1101.stackexchangeapp.core.ui.base.BaseFragment
import com.github.emilg1101.stackexchangeapp.domain.repository.QuestionsRepository
import com.github.emilg1101.stackexchangeapp.domain.repository.TagsRepository
import com.github.emilg1101.stackexchangeapp.questions.R
import com.github.emilg1101.stackexchangeapp.questions.adapter.QuestionsPagingAdapter
import com.github.emilg1101.stackexchangeapp.questions.databinding.FragmentQuestionsBinding
import com.github.emilg1101.stackexchangeapp.questions.di.questionsComponent
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_questions.chip_filter
import kotlinx.android.synthetic.main.fragment_questions.chip_sort
import kotlinx.android.synthetic.main.fragment_questions.list
import kotlinx.android.synthetic.main.fragment_questions.tags_group
import kotlinx.android.synthetic.main.fragment_questions.toolbar
import kotlinx.android.synthetic.main.bottom_sheet_filter.available_tags_group
import kotlinx.android.synthetic.main.bottom_sheet_filter.bottom_sheet
import kotlinx.android.synthetic.main.bottom_sheet_filter.chip_hot_sort
import kotlinx.android.synthetic.main.bottom_sheet_filter.chip_month_sort
import kotlinx.android.synthetic.main.bottom_sheet_filter.chip_week_sort

class QuestionsSearchFragment :
    BaseFragment<FragmentQuestionsBinding>(R.layout.fragment_questions) {

    lateinit var adapter: QuestionsPagingAdapter

    override val viewModel: QuestionsViewModel by viewModels {
        questionsComponent.provideViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        questionsComponent.inject(this)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sheetBehavior = BottomSheetBehavior.from(bottom_sheet)

        setToolbar(toolbar)

        adapter.onQuestionClick = {
            viewModel.openQuestion(it)
        }

        list.layoutManager = LinearLayoutManager(requireContext())
        list.adapter = adapter

        chip_filter.setOnClickListener {
            if (sheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
            } else {
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
            }
        }

        chip_hot_sort.setOnCheckedChangeListener { buttonView, isChecked -> if (isChecked) viewModel.changeSort(Sort("hot")) }
        chip_week_sort.setOnCheckedChangeListener { buttonView, isChecked -> if (isChecked) viewModel.changeSort(Sort("week")) }
        chip_month_sort.setOnCheckedChangeListener { buttonView, isChecked -> if (isChecked) viewModel.changeSort(Sort("month")) }

        viewModel.selectedTagsLiveData.observe(viewLifecycleOwner) { tags ->
            if (tags_group.childCount > 2) {
                tags_group.removeViews(2, tags_group.childCount - 2)
            }
            tags.map {
                (layoutInflater.inflate(R.layout.chip_close, tags_group, false) as? Chip)?.apply {
                    text = it
                    setOnCloseIconClickListener { chip ->
                        tags_group.removeView(chip)
                        viewModel.addTag(it)
                    }
                }
            }.forEach {
                tags_group.addView(it)
            }
        }

        viewModel.tagsLiveData.observe(viewLifecycleOwner) { tags ->
            available_tags_group.removeAllViews()
            tags.map {
                (layoutInflater.inflate(R.layout.chip_default, available_tags_group, false) as? Chip)?.apply {
                    text = it.title
                    isChecked = it.selected
                    setOnCheckedChangeListener { view, _ ->
                        viewModel.addTag(it.title)
                    }
                }
            }.forEach {
                available_tags_group.addView(it)
            }
        }

        viewModel.pagedListLiveData.observe(viewLifecycleOwner) { liveData ->
            liveData.observe(viewLifecycleOwner) { questions ->
                adapter.submitList(questions)
            }
        }

        viewModel.sortLiveData.observe(viewLifecycleOwner) { sort ->
            chip_sort.text = sort.sort
            when (sort.sort) {
                "hot" -> chip_hot_sort.isCheckable = true
                "week" -> chip_week_sort.isCheckable = true
                "month" -> chip_month_sort.isCheckable = true
            }
        }
    }
}

class QuestionsSearchViewModelFactory internal constructor(
    private val questionsRepository: QuestionsRepository,
    private val tagsRepository: TagsRepository,
    private val questionsNavigation: QuestionsNavigation
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        QuestionsViewModel(
            questionsRepository,
            tagsRepository,
            questionsNavigation
        ) as T
}

class Sort(var sort: String)

val HotSort = Sort("hot")
