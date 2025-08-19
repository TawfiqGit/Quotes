package com.tawfiqdev.quotesapp.data

data class Quote (
    val id : Int,
    val icon : Int,
    val content : String,
    val author : String,
    val year: Int
)