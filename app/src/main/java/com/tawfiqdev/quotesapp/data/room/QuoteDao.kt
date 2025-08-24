package com.tawfiqdev.quotesapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {
    @Insert
    fun addNewQuote(quoteEntity: QuoteEntity)

    @Update
    fun updateQuote(quoteEntity: QuoteEntity)

    @Query("DELETE FROM quote_table")
    fun deleteAllQuote()

    @Query("DELETE FROM quote_table WHERE id = :id")
    fun deleteQuoteById(id: Int)

    @Query("SELECT * FROM quote_table ORDER BY author ASC")
    fun observeQuotesByAuthorAZ(): Flow<List<QuoteEntity>>

    @Query("SELECT * FROM quote_table ORDER BY author DESC")
    fun observeQuotesByAuthorZA(): Flow<List<QuoteEntity>>

    @Query("SELECT * FROM quote_table ORDER BY year DESC, id DESC")
    fun observeQuotesByYearNewest(): Flow<List<QuoteEntity>>

    @Query("SELECT * FROM quote_table ORDER BY year ASC, id ASC")
    fun observeQuotesByYearOldest(): Flow<List<QuoteEntity>>

    @Query("UPDATE quote_table SET thumbs_up = thumbs_up + 1 WHERE id = :id")
    suspend fun incrementThumbsUp(id: Long)

    @Query("UPDATE quote_table SET thumbs_down = thumbs_down + 1 WHERE id = :id")
    suspend fun incrementThumbsDown(id: Long)
}
