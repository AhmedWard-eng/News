package com.example.news.data.repo.news

import com.example.news.domin.model.News
import kotlinx.coroutines.flow.Flow

class NewsRepoImp :NewsRepo{
    override fun getNews(): Flow<List<News>> {
        TODO("Not yet implemented")
    }

    override fun getFavorites(): Flow<List<News>> {
        TODO("Not yet implemented")
    }

    override suspend fun addToFavorites(news: News): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun removeFromFavorites(news: News): Result<Boolean> {
        TODO("Not yet implemented")
    }


}