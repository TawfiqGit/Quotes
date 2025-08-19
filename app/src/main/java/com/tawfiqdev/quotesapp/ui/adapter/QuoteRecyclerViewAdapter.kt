package com.tawfiqdev.quotesapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tawfiqdev.quotesapp.databinding.ItemQuoteBinding
import com.tawfiqdev.quotesapp.data.Quote

class QuoteRecyclerViewAdapter(
    private val onQuoteClick: (Quote) -> Unit = {}
) : ListAdapter<Quote, QuoteRecyclerViewAdapter.ViewHolder>(DIFF) {

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Quote>() {
            override fun areItemsTheSame(oldItem: Quote, newItem: Quote) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Quote, newItem: Quote) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemQuoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onQuoteClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class ViewHolder(
        private val itemBinding: ItemQuoteBinding,
        private val onQuoteClick: (Quote) -> Unit
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(quote: Quote) {
            itemBinding.textContent.text = quote.content
            itemBinding.textAuthor.text = quote.author
            itemBinding.textYear.text = quote.year.toString()
            itemBinding.root.setOnClickListener { onQuoteClick(quote) }
        }
    }
}