package com.github.emilg1101.stackexchangeapp.questionssearch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.github.emilg1101.stackexchangeapp.questionssearch.R
import com.github.emilg1101.stackexchangeapp.questionssearch.model.QuestionItemModel
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.item_question.view.*

class QuestionsPagingAdapter :
    PagedListAdapter<QuestionItemModel, QuestionsPagingAdapter.QuestionItemViewHolder>(
        QUESTION_ITEM_COMPARATOR
    ) {

    var onQuestionClick: ((QuestionItemModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        return QuestionItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class QuestionItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: QuestionItemModel?) = with(itemView) {
            text_title.text = item?.title
            tags.removeAllViews()
            item?.tags?.forEach {
                tags.addView(Chip(context).apply {
                    text = it
                })
            }
            Glide.with(context)
                .load(item?.ownerImage)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(image_owner)
            setOnClickListener { item?.let { onQuestionClick?.invoke(it) } }
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
