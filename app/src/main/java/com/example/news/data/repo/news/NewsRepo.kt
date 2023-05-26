package com.example.news.data.repo.news

import com.example.news.domin.model.News

interface NewsRepo {
    suspend fun getNews() : List<News>
    suspend fun getFavorites() : List<News>
    suspend fun addToFavorites(news: News)
    suspend fun removeFromFavorites(news: News)
}