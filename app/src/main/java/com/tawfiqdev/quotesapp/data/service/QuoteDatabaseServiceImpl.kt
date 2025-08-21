package com.tawfiqdev.quotesapp.data.service

import com.tawfiqdev.quotesapp.data.room.QuoteDao
import com.tawfiqdev.quotesapp.data.room.QuoteEntity
import javax.inject.Inject

class QuoteDatabaseServiceImpl @Inject constructor(
    private val quoteDao: QuoteDao
) : QuoteDatabaseService {

    override fun addNewQuote(quoteEntity: QuoteEntity) = quoteDao.addNewQuote(quoteEntity)

    override fun getQuote(): List<QuoteEntity> = quoteDao.getQuote()

    override fun updateQuote(quoteEntity: QuoteEntity) = quoteDao.updateQuote(quoteEntity)

    override fun deleteAllQuote() = quoteDao.deleteAllQuote()

    override fun deleteQuoteById(id: Int) = quoteDao.deleteQuoteById(id)
}