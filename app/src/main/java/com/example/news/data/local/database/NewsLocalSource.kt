package com.example.news.data.local.database

import com.example.news.data.local.entity.FavNews
import com.example.news.data.local.entity.LocalNews
import kotlinx.coroutines.flow.Flow

class NewsLocalSource(private val newsDAO: NewsDAO =NewsDatabase.getInstance().getNewsDAO()) : LocalSource {


    override suspend fun insertNews(localNews: LocalNews) {
       newsDAO.insertNews(localNews)
    }

    override fun getAllNews(): Flow<List<LocalNews>> {
      return newsDAO.getAllNews()
    }

    override suspend fun deleteNews(localNews: LocalNews) {
       newsDAO.deleteNews(localNews)
    }

    override fun getFavNewsWithTitle(title: String): Flow<LocalNews> {
       return  newsDAO.getFavNewsWithTitle(title)
    }

    override suspend fun insertFavNews(favNews: FavNews) {
        newsDAO.insertFavNews(favNews)
    }

    override fun getAllFavNews(): Flow<List<FavNews>> {
       return newsDAO.getAllFavNews()
    }

    override suspend fun deleteFavNews(favNews: FavNews) {
        newsDAO.deleteFavNews(favNews)
    }
}