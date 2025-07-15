package com.tawfiqdev.quotesapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tawfiqdev.quotesapp.databinding.ItemQuoteBinding
import com.tawfiqdev.quotesapp.model.Quote

class QuoteRecyclerViewAdapter : RecyclerView.Adapter<QuoteRecyclerViewAdapter.ViewHolder>() {

    private val adapterData = mutableListOf<Quote>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = ItemQuoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = adapterData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quote  = adapterData[position]
        holder.bind(quote)

        holder.itemView.setOnClickListener {

        }
    }

    fun setData(data: List<Quote>) {
        adapterData.apply {
            clear()
            addAll(data)
        }
    }

    class ViewHolder(
        private val itemBinding: ItemQuoteBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(quote: Quote){
            itemBinding.textContent.text = quote.content
            itemBinding.textAuthor.text = quote.author
            itemBinding.textYear.text = quote.year.toString()
        }
    }
}