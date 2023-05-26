package com.example.news.data.repo.news

import com.example.news.domin.model.News

class NewsRepoImp :NewsRepo{
    override suspend fun getNews(): List<News> {
        TODO("Not yet implemented")
    }

    override suspend fun getFavorites(): List<News> {
        TODO("Not yet implemented")
    }

    override suspend fun addToFavorites(news: News) {
        TODO("Not yet implemented")
    }

    override suspend fun removeFromFavorites(news: News) {
        TODO("Not yet implemented")
    }

}