package com.tawfiqdev.quotesapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.tawfiqdev.quotesapp.data.model.SpinnerItem
import com.tawfiqdev.quotesapp.databinding.ItemsSpinnerBinding

class SortBySpinnerAdapter (
    private val context: Context,
    private val items: List<SpinnerItem>
) : BaseAdapter(){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: ItemsSpinnerBinding
        val view: View

        if (convertView == null) {
            binding = ItemsSpinnerBinding.inflate(LayoutInflater.from(context), parent, false)
            view = binding.root
            view.tag = binding
        } else {
            view = convertView
            binding = view.tag as ItemsSpinnerBinding
        }

        val item = getItem(position)
        binding.spinnerIcon.setImageResource(item.iconRes)
        binding.spinnerText.text = item.text

        return view
    }

    override fun getCount(): Int = items.size

    override fun getItem(position: Int) = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View  = getView(position, convertView, parent)
}