package com.github.emilg1101.stackexchangeapp.questionssearch.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.github.emilg1101.stackexchangeapp.questionssearch.R

class SortSpinnerAdapter(context: Context, private val items: Array<String>) :
    ArrayAdapter<String>(context, R.layout.item_sort_spinner_dropdown, items) {

    private val inflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        if (convertView == null || convertView.tag.toString() != "NON_DROPDOWN") {
            val view = inflater.inflate(R.layout.item_sort_spinner_actionbar, parent, false)
            view.tag = "NON_DROPDOWN"
            return getCustomView(position, view)
        }
        return convertView
    }

    override fun getDropDownView(
        position: Int,
        view: View?,
        parent: ViewGroup?
    ): View? {
        var convertView = view
        if (convertView == null || convertView.tag.toString() != "DROPDOWN") {
            convertView = inflater.inflate(R.layout.item_sort_spinner_dropdown, parent, false)
            convertView.tag = "DROPDOWN"
        }
        return getCustomView(position, convertView)
    }

    private fun getCustomView(position: Int, convertView: View?): View {
        val tvCategory = convertView?.findViewById(R.id.sort) as TextView
        tvCategory.text = items[position]
        return convertView
    }
}
