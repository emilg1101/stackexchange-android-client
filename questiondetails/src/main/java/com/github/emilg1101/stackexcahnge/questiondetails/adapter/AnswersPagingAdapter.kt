package com.github.emilg1101.stackexcahnge.questiondetails.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.github.emilg1101.stackexcahnge.questiondetails.R
import com.github.emilg1101.stackexcahnge.questiondetails.model.AnswerItemModel
import com.github.rjeschke.txtmark.Processor
import kotlinx.android.synthetic.main.item_answer.view.*

class AnswersPagingAdapter :
    PagedListAdapter<AnswerItemModel, AnswersPagingAdapter.AnswerItemViewHolder>(
        QUESTION_ITEM_COMPARATOR
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_answer, parent, false)
        return AnswerItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnswerItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AnswerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: AnswerItemModel?) = with(itemView) {
            body.loadData(Processor.process(item?.body), "text/html", "UTF-8")
            image_checked.visibility = if (item?.accepted == true) View.VISIBLE else View.GONE
            Glide.with(context)
                .load(item?.ownerImage)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(image_owner)
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
