package com.tawfiqdev.quotesapp.data.service

import com.tawfiqdev.quotesapp.data.room.QuoteEntity

interface QuoteDatabaseService {
    fun addNewQuote(quoteEntity: QuoteEntity)
    fun getQuote(): List<QuoteEntity>
    fun updateQuote(quoteEntity: QuoteEntity)
    fun deleteAllQuote()
    fun deleteQuoteById(id: Int)
}