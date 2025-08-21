package com.tawfiqdev.quotesapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface QuoteDao {
    @Insert
    fun addNewQuote(quoteEntity: QuoteEntity)

    @Query("SELECT * FROM quote_table")
    fun getQuote(): List<QuoteEntity>

    @Update
    fun updateQuote(quoteEntity: QuoteEntity)

    @Query("DELETE FROM quote_table")
    fun deleteAllQuote()

    @Query("DELETE FROM quote_table WHERE id = :id")
    fun deleteQuoteById(id: Int)
}
