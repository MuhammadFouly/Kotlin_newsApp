package com.example.foulynewsapp.models

import com.example.foulynewsapp.models.Article

data class newsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)