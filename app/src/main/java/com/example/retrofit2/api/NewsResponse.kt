package com.example.retrofit2.api

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)