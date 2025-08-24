package com.tawfiqdev.quotesapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tawfiqdev.quotesapp.databinding.ItemQuoteBinding
import com.tawfiqdev.quotesapp.data.room.QuoteEntity

class QuoteRecyclerViewAdapter(
    private val onUp: (Long) -> Unit,
    private val onDown: (Long) -> Unit
) : ListAdapter<QuoteEntity, QuoteRecyclerViewAdapter.ViewHolder>(DIFF) {

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<QuoteEntity>() {
            override fun areItemsTheSame(oldItem: QuoteEntity, newItem: QuoteEntity) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: QuoteEntity, newItem: QuoteEntity) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemQuoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onUp , onDown)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class ViewHolder(
        private val itemBinding: ItemQuoteBinding,
        private val onUp: (Long) -> Unit,
        private val onDown: (Long) -> Unit
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(quoteEntity: QuoteEntity) {
            itemBinding.textContent.text = quoteEntity.content
            itemBinding.textAuthor.text = quoteEntity.author
            itemBinding.textYear.text = quoteEntity.year.toString()
            itemBinding.textNumberThumbUp.text = quoteEntity.thumbsUp.toString()
            itemBinding.textNumberThumbDown.text = quoteEntity.thumbsDown.toString()

            itemBinding.radioButtonThumbUp.setOnClickListener {
                onUp(quoteEntity.id.toLong())
                itemBinding.radioButtonThumbUp.isChecked = false
            }

            itemBinding.radioButtonThumbDown.setOnClickListener {
                onDown(quoteEntity.id.toLong())
                itemBinding.radioButtonThumbDown.isChecked = false
            }
        }
    }
}