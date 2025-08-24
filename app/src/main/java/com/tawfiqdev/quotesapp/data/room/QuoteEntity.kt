package com.tawfiqdev.quotesapp.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote_table")
data class QuoteEntity (
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "icon") val icon : Int,
    @ColumnInfo(name = "content") val content : String,
    @ColumnInfo(name = "author") val author : String,
    @ColumnInfo(name = "year") val year: Int,
    @ColumnInfo(name = "thumbs_up") val thumbsUp: Int,
    @ColumnInfo(name = "thumbs_down") val thumbsDown: Int,
)