package com.tawfiqdev.quotesapp.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tawfiqdev.quotesapp.R
import com.tawfiqdev.quotesapp.databinding.ActivityQuoteBinding
import com.tawfiqdev.quotesapp.model.Quote
import com.tawfiqdev.quotesapp.ui.adapter.QuoteRecyclerViewAdapter

class QuoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuoteBinding

    private val accountRecyclerviewAdapter: QuoteRecyclerViewAdapter by lazy {
        QuoteRecyclerViewAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        setupClickListeners()
        setupRecyclerView()
    }

    private fun setupClickListeners() {

    }

    private fun setupRecyclerView() {
        accountRecyclerviewAdapter.setData(listQuote())

        binding.contentMain.recyclerviewQuote.apply {
            layoutManager = LinearLayoutManager(this@QuoteActivity)
            hasFixedSize()
            this.adapter = accountRecyclerviewAdapter
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
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
            content = "Le roi des pirates, ce sera moi ! ",
            author = "Monkey D.Luffy",
            year = 2001
        ) ,
        Quote(
            id = 3,
            icon = 789,
            content = "Ne pas voir la pourriture de ce monde, est un plaisir uniquement connu des aveugles ",
            author = "Fujitora",
            year = 1987
        )
    )
}