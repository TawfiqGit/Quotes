package com.tawfiqdev.quotesapp.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.tawfiqdev.quotesapp.R
import com.tawfiqdev.quotesapp.data.room.QuoteEntity
import com.tawfiqdev.quotesapp.data.service.QuoteDatabaseService
import com.tawfiqdev.quotesapp.databinding.ActivityQuoteBinding
import com.tawfiqdev.quotesapp.ui.adapter.QuoteRecyclerViewAdapter
import com.tawfiqdev.quotesapp.ui.fragment.dialog.EditQuoteDialogFragment
import com.tawfiqdev.quotesapp.ui.presentation.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 *  Sert à attacher le conteneur Hilt local et permettre @Inject.
 *  Seulement Activity, Fragment, Service... peuvent être attachés.
 */
@AndroidEntryPoint
class QuoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuoteBinding

    private val viewModel: QuoteViewModel by viewModels()

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
        observeQuotes()
        setupClickListeners()
    }

    /**
     *  Room interdit d’appeler la DB sur le main thread (sinon IllegalStateException)
     *  Flow + asLiveData(), toute modification (insert/delete/update) rafraîchit automatiquement la liste observée
     */
    private fun observeQuotes() {
        viewModel.quotesLiveData.observe(this){ it ->
            quoteAdapter.submitList(it) {
                binding.contentMain.recyclerviewQuote.scrollToPosition(it.lastIndex)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.contentMain.recyclerviewQuote.apply {
            layoutManager = LinearLayoutManager(this@QuoteActivity)
            setHasFixedSize(true)
            adapter = quoteAdapter
        }
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

            val newItem = QuoteEntity(id = id, icon = icon, content = content, author = author, year = year)
            viewModel.addQuote(newItem)
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
}