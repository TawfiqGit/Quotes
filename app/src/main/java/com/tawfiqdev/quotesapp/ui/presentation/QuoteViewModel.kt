package com.tawfiqdev.quotesapp.ui.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.tawfiqdev.quotesapp.data.room.QuoteEntity
import com.tawfiqdev.quotesapp.data.service.QuoteDatabaseService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class SortType {
    BY_AUTHOR_AZ,
    BY_AUTHOR_ZA,
    BY_YEAR_ASC,
    BY_YEAR_DESC
}

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val service: QuoteDatabaseService
) : ViewModel() {

    private fun source(sort: SortType): Flow<List<QuoteEntity>> = when (sort) {
        SortType.BY_AUTHOR_AZ -> service.observeQuotesByAuthorAZ()
        SortType.BY_AUTHOR_ZA -> service.observeQuotesByAuthorZA()
        SortType.BY_YEAR_ASC -> service.observeQuotesByYearNewest()
        SortType.BY_YEAR_DESC -> service.observeQuotesByYearOldest()
    }

    fun quotesLiveData(sort: SortType): LiveData<List<QuoteEntity>> = source(sort).asLiveData()

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