package com.example.news.data.repo.news

import com.example.news.data.local.entity.FavNews
import com.example.news.domin.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepo {
    fun getNews() : Flow<List<News>>
    fun getFavorites() : Flow<List<News>>
    suspend fun addToFavorites(news: News) : Result<Boolean>
    suspend fun removeFromFavorites(news: News) : Result<Boolean>
    suspend fun refreshNews()
    suspend fun getFavNewsWithTitle(title: String) : FavNews?
}