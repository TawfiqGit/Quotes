package com.tawfiqdev.quotesapp.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tawfiqdev.quotesapp.R
import com.tawfiqdev.quotesapp.data.Quote
import com.tawfiqdev.quotesapp.databinding.ActivityQuoteBinding
import com.tawfiqdev.quotesapp.ui.adapter.QuoteRecyclerViewAdapter
import com.tawfiqdev.quotesapp.ui.fragment.dialog.EditQuoteDialogFragment

class QuoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuoteBinding

    private val quoteAdapter: QuoteRecyclerViewAdapter by lazy {
        QuoteRecyclerViewAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        setupRecyclerView()
        observeDialogResults()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        binding.contentMain.recyclerviewQuote.apply {
            layoutManager = LinearLayoutManager(this@QuoteActivity)
            setHasFixedSize(true)
            adapter = quoteAdapter
        }
        quoteAdapter.submitList(listQuote())
    }

    private fun setupClickListeners() {
        binding.contentAction.buttonAddQuote.setOnClickListener {
            Log.i("Quote","Opening dialog")
            EditQuoteDialogFragment().show(supportFragmentManager, "EditQuoteDialog")
        }
    }

    private fun observeDialogResults() {
        supportFragmentManager.setFragmentResultListener(EditQuoteDialogFragment.RESULT_KEY, this) { _, bundle ->
            val id = bundle.getInt(EditQuoteDialogFragment.ARG_ID)
            val icon = bundle.getInt(EditQuoteDialogFragment.ARG_ICON)
            val content = bundle.getString(EditQuoteDialogFragment.ARG_CONTENT).orEmpty()
            val author = bundle.getString(EditQuoteDialogFragment.ARG_AUTHOR).orEmpty()
            val year = bundle.getInt(EditQuoteDialogFragment.ARG_YEAR)

            Log.i("Quote","observeDialogResults : $id, $icon, $content, $author, $year")

            val newList = quoteAdapter.currentList.toMutableList().apply {
                add(Quote(id = id, icon = icon, content = content, author = author, year = year))
            }
            quoteAdapter.submitList(newList) {
                binding.contentMain.recyclerviewQuote.scrollToPosition(newList.lastIndex)
                Log.i("Quote","submitList done. size=${newList.size}")
            }
        }
    }

    private fun listQuote(): List<Quote> = listOf(
        Quote(
            id = 1,
            icon = 789,
            content = "Vivre n’est pas un crime",
            author = "Franky",
            year = 1995
        ),
        Quote(
            id = 2,
            icon = 789,
            content = "Le roi des pirates, ce sera moi !",
            author = "Monkey D. Luffy",
            year = 2001
        ),
        Quote(
            id = 3,
            icon = 789,
            content = "Ne pas voir la pourriture de ce monde est un plaisir uniquement connu des aveugles",
            author = "Fujitora",
            year = 1987
        )
    )

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                // Hook for settings action
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}