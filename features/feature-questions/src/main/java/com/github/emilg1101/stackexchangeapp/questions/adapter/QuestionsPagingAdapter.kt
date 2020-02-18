package com.github.emilg1101.stackexchangeapp.questions.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.emilg1101.stackexchangeapp.core.binding.ChipClickListener
import com.github.emilg1101.stackexchangeapp.questions.R
import com.github.emilg1101.stackexchangeapp.questions.databinding.ItemQuestionBinding
import com.github.emilg1101.stackexchangeapp.questions.model.QuestionItemModel

class QuestionsPagingAdapter :
    PagedListAdapter<QuestionItemModel, QuestionsPagingAdapter.QuestionItemViewHolder>(
        QUESTION_ITEM_COMPARATOR
    ) {

    var onQuestionClick: ((QuestionItemModel) -> Unit)? = null
    var onTagClick: ((String) -> Unit)? = null
    var onProfileClick: ((QuestionItemModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionItemViewHolder {
        val binding = DataBindingUtil.inflate<ItemQuestionBinding>(LayoutInflater.from(parent.context), R.layout.item_question, parent, false)
        return QuestionItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class QuestionItemViewHolder(private val binding: ItemQuestionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: QuestionItemModel?) = with(binding) {
            binding.itemModel = item
            binding.chipClickListener = object : ChipClickListener {
                override fun click(text: String) {
                    onTagClick?.invoke(text)
                }
            }
            profile.setOnClickListener { item?.let { onProfileClick?.invoke(it) } }
            root.setOnClickListener { item?.let { onQuestionClick?.invoke(it) } }
        }
    }

    companion object {
        private val QUESTION_ITEM_COMPARATOR = object : DiffUtil.ItemCallback<QuestionItemModel>() {
            override fun areItemsTheSame(
                oldItem: QuestionItemModel,
                newItem: QuestionItemModel
            ): Boolean =
                oldItem.questionId == newItem.questionId

            override fun areContentsTheSame(
                oldItem: QuestionItemModel,
                newItem: QuestionItemModel
            ): Boolean =
                oldItem == newItem
        }
    }
}
