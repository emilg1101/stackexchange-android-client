package com.github.emilg1101.stackexcahnge.questiondetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.emilg1101.stackexcahnge.questiondetails.R
import com.github.emilg1101.stackexcahnge.questiondetails.databinding.ItemCommentBinding
import com.github.emilg1101.stackexcahnge.questiondetails.model.CommentItemModel

class CommentsPagingAdapter : PagedListAdapter<CommentItemModel, CommentsPagingAdapter.CommentItemViewHolder>(COMMENT_ITEM_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentItemViewHolder {
        val binding = DataBindingUtil.inflate<ItemCommentBinding>(LayoutInflater.from(parent.context), R.layout.item_comment, parent, false)
        return CommentItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CommentItemViewHolder(private val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CommentItemModel?) = with(binding) {
            itemModel = item
        }
    }

    companion object {
        private val COMMENT_ITEM_COMPARATOR = object : DiffUtil.ItemCallback<CommentItemModel>() {
            override fun areItemsTheSame(
                oldItem: CommentItemModel,
                newItem: CommentItemModel
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: CommentItemModel,
                newItem: CommentItemModel
            ): Boolean =
                oldItem == newItem
        }
    }
}
