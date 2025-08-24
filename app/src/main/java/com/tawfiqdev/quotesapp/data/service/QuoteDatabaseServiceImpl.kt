package com.tawfiqdev.quotesapp.data.service

import com.tawfiqdev.quotesapp.data.room.QuoteDao
import com.tawfiqdev.quotesapp.data.room.QuoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/***
 * But = clean architecture (DAO → Service → ViewModel/UI).
 * Ajouter des règles métier (ex: validation, logs, cache) avant d’appeler la DB.
 * **/
class QuoteDatabaseServiceImpl @Inject constructor(
    private val quoteDao: QuoteDao
) : QuoteDatabaseService {

    override suspend fun addNewQuote(quoteEntity: QuoteEntity) = quoteDao.addNewQuote(quoteEntity)

    override suspend fun updateQuote(quoteEntity: QuoteEntity) = quoteDao.updateQuote(quoteEntity)

    override suspend fun deleteAllQuote() = quoteDao.deleteAllQuote()

    override suspend fun deleteQuoteById(id: Int) = quoteDao.deleteQuoteById(id)

    override suspend fun incrementThumbsUp(id: Long) = quoteDao.incrementThumbsUp(id)

    override suspend fun incrementThumbsDown(id: Long) =  quoteDao.incrementThumbsDown(id)

    override fun observeQuotesByAuthorAZ(): Flow<List<QuoteEntity>> = quoteDao.observeQuotesByAuthorAZ()

    override fun observeQuotesByAuthorZA(): Flow<List<QuoteEntity>> = quoteDao.observeQuotesByAuthorZA()

    override fun observeQuotesByYearNewest(): Flow<List<QuoteEntity>> = quoteDao.observeQuotesByYearNewest()

    override fun observeQuotesByYearOldest(): Flow<List<QuoteEntity>> = quoteDao.observeQuotesByYearOldest()
}
