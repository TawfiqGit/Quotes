package com.tawfiqdev.quotesapp.model

data class Quote (
    val id : Int,
    val icon : Int,
    val content : String,
    val author : String,
    val year: Int
)