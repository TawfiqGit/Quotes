package com.tawfiqdev.quotesapp.data.service

import com.tawfiqdev.quotesapp.data.room.QuoteEntity
import kotlinx.coroutines.flow.Flow


/**
 * Abstraction du DAO
 * Permet de découpler le code métier de Room directement
 * suspend pour eviter le main thread
 */
interface QuoteDatabaseService {

    suspend fun addNewQuote(quoteEntity: QuoteEntity)

    suspend fun updateQuote(quoteEntity: QuoteEntity)

    suspend fun deleteAllQuote()

    suspend fun deleteQuoteById(id: Int)

    fun observeQuotesByAuthorAZ(): Flow<List<QuoteEntity>>

    fun observeQuotesByAuthorZA(): Flow<List<QuoteEntity>>

    fun observeQuotesByYearNewest(): Flow<List<QuoteEntity>>

    fun observeQuotesByYearOldest(): Flow<List<QuoteEntity>>
}