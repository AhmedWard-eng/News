package com.example.news.data.remote.entity.news


data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)