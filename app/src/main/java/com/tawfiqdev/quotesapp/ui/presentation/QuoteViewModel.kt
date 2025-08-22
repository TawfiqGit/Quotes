package com.tawfiqdev.quotesapp.ui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.tawfiqdev.quotesapp.data.room.QuoteEntity
import com.tawfiqdev.quotesapp.data.service.QuoteDatabaseService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val service: QuoteDatabaseService
) : ViewModel() {

    val quotesLiveData: androidx.lifecycle.LiveData<List<QuoteEntity>> =
        service.observeQuotes().asLiveData()

    fun addQuote(item: QuoteEntity) {
        viewModelScope.launch(kotlinx.coroutines.Dispatchers.IO) {
            service.addNewQuote(item)
        }
    }

    fun deleteById(id: Int) {
        viewModelScope.launch(kotlinx.coroutines.Dispatchers.IO) {
            service.deleteQuoteById(id)
        }
    }

    fun clearAll() {
        viewModelScope.launch(kotlinx.coroutines.Dispatchers.IO) {
            service.deleteAllQuote()
        }
    }
}
