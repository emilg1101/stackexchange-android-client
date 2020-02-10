package com.github.emilg1101.stackexcahnge.questiondetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.emilg1101.stackexcahnge.questiondetails.R
import com.github.emilg1101.stackexcahnge.questiondetails.databinding.ItemAnswerBinding
import com.github.emilg1101.stackexcahnge.questiondetails.model.AnswerItemModel

class AnswersPagingAdapter :
    PagedListAdapter<AnswerItemModel, AnswersPagingAdapter.AnswerItemViewHolder>(
        QUESTION_ITEM_COMPARATOR
    ) {

    var onCommentClick: ((AnswerItemModel) -> Unit)? = null
    var onAnswerClick: ((AnswerItemModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerItemViewHolder {
        val binding = DataBindingUtil.inflate<ItemAnswerBinding>(LayoutInflater.from(parent.context), R.layout.item_answer, parent, false)
        return AnswerItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnswerItemViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class AnswerItemViewHolder(private val binding: ItemAnswerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AnswerItemModel) = with(binding) {
            itemModel = item
            profile.setOnClickListener { it }
            commentCount.setOnClickListener {
                onCommentClick?.invoke(item)
            }
            itemView.setOnClickListener { onAnswerClick?.invoke(item) }
        }
    }

    companion object {
        private val QUESTION_ITEM_COMPARATOR = object : DiffUtil.ItemCallback<AnswerItemModel>() {
            override fun areItemsTheSame(
                oldItem: AnswerItemModel,
                newItem: AnswerItemModel
            ): Boolean =
                oldItem.answerId == newItem.answerId

            override fun areContentsTheSame(
                oldItem: AnswerItemModel,
                newItem: AnswerItemModel
            ): Boolean =
                oldItem == newItem
        }
    }
}
