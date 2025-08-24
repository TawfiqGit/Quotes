package com.tawfiqdev.quotesapp.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tawfiqdev.quotesapp.R
import com.tawfiqdev.quotesapp.data.model.SpinnerItem
import com.tawfiqdev.quotesapp.data.room.QuoteEntity
import com.tawfiqdev.quotesapp.databinding.ActivityQuoteBinding
import com.tawfiqdev.quotesapp.ui.adapter.QuoteRecyclerViewAdapter
import com.tawfiqdev.quotesapp.ui.adapter.SortBySpinnerAdapter
import com.tawfiqdev.quotesapp.ui.fragment.dialog.EditQuoteDialogFragment
import com.tawfiqdev.quotesapp.ui.presentation.QuoteViewModel
import com.tawfiqdev.quotesapp.ui.presentation.SortType
import dagger.hilt.android.AndroidEntryPoint

/**
 *  Sert à attacher le conteneur Hilt local et permettre @Inject.
 *  Seulement Activity, Fragment, Service... peuvent être attachés.
 */
@AndroidEntryPoint
class QuoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuoteBinding

    private val viewModel: QuoteViewModel by viewModels()

    private val quoteAdapter: QuoteRecyclerViewAdapter by lazy {
        QuoteRecyclerViewAdapter(
            onUp = { viewModel.onThumbUpClick(it) },
            onDown = { viewModel.onThumbDownClick(it) }
        )
    }

    private val spinnerItems = listOf(
        SpinnerItem(R.drawable.ic_sort_by_az, "A-Z"),
        SpinnerItem(R.drawable.ic_sort_by_az, "Z-A"),
        SpinnerItem(R.drawable.ic_most_older, "Plus recent"),
        SpinnerItem(R.drawable.ic_most_recent, "Plus ancien")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        setupRecyclerView()
        setupSpinner()
        observeDialogResults()
        observeQuotes(SortType.BY_AUTHOR_AZ)
        setupAction()
    }

    private fun setupSpinner() {
        val adapter = SortBySpinnerAdapter(
            context = this,
            items = spinnerItems
        )
        binding.contentAction.spinnerSort.setSelection(0)
        binding.contentAction.spinnerSort.adapter = adapter
    }

    /**
     *  Room interdit d’appeler la DB sur le main thread (sinon IllegalStateException)
     *  Flow + asLiveData(), toute modification (insert/delete/update) rafraîchit automatiquement la liste observée
     */
    private fun observeQuotes(sortType: SortType) {
        viewModel.quotesLiveData(sortType).observe(this){
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

    private fun setupAction() {
        binding.contentAction.buttonAddQuote.setOnClickListener {
            EditQuoteDialogFragment().show(supportFragmentManager, "EditQuoteDialog")
        }

        binding.contentAction.spinnerSort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val sort = when (position) {
                    0 -> SortType.BY_AUTHOR_AZ
                    1 -> SortType.BY_AUTHOR_ZA
                    2 -> SortType.BY_YEAR_ASC
                    else -> SortType.BY_YEAR_DESC
                }
                observeQuotes(sort)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        binding.contentAction.buttonClear.setOnClickListener {
            viewModel.clearAll()
        }
    }

    private fun observeDialogResults() {
        supportFragmentManager.setFragmentResultListener(EditQuoteDialogFragment.RESULT_KEY, this) { _, bundle ->
            val id = bundle.getInt(EditQuoteDialogFragment.ARG_ID)
            val icon = bundle.getInt(EditQuoteDialogFragment.ARG_ICON)
            val content = bundle.getString(EditQuoteDialogFragment.ARG_CONTENT).orEmpty()
            val author = bundle.getString(EditQuoteDialogFragment.ARG_AUTHOR).orEmpty()
            val year = bundle.getInt(EditQuoteDialogFragment.ARG_YEAR)

            val newItem = QuoteEntity(
                id = id, icon = icon, content = content, author = author, year = year, thumbsUp = 0, thumbsDown = 0
            )
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