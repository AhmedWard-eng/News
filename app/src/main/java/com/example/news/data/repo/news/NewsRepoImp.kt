package com.example.news.data.repo.news

import android.util.Log
import com.example.news.data.local.database.LocalSource
import com.example.news.data.local.database.NewsLocalSource
import com.example.news.data.local.entity.FavNews
import com.example.news.data.local.entity.toNews
import com.example.news.data.remote.datasource.news.NewsRemoteData
import com.example.news.data.remote.datasource.news.NewsRemoteDataImp
import com.example.news.data.remote.entity.toLocalNews
import com.example.news.domin.model.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class NewsRepoImp(
    private val newsRemoteData: NewsRemoteData = NewsRemoteDataImp(),
    private val newLocalSource: LocalSource = NewsLocalSource()
) : NewsRepo {


    override suspend fun refreshNews() {
        try {
            val remoteNews = newsRemoteData.getNews()
            val localNews = remoteNews.map { it.toLocalNews() }
            newLocalSource.insertNews(localNews)
        } catch (e: Exception) {
            Log.e("Network Error", "refreshNews: ${e.message} ")
        }
    }

    override fun getNews(): Flow<List<News>> {
        return newLocalSource.getAllFavNews().combine(newLocalSource.getAllNews()) { fav, news ->
            news.map {
                val favorite = fav.firstOrNull { favNews -> favNews.title==it.title }
                it.toNews(isFav = favorite != null )
            }
        }
    }

    override fun getFavorites(): Flow<List<News>> {
        return newLocalSource.getAllFavNews().combine(newLocalSource.getAllNews()) { fav, news ->
            fav.map {
                news.first { newsItem -> newsItem.title == it.title }.toNews(true)
            }
        }
    }

    override suspend fun addToFavorites(news: News): Result<Boolean> {
        newLocalSource.insertFavNews(FavNews(news.title))
        return Result.success(true)
    }

    override suspend fun removeFromFavorites(news: News): Result<Boolean> {
        newLocalSource.deleteFavNews(news.title)
        return Result.success(true)
    }

    override suspend fun getFavNewsWithTitle(title: String) : FavNews?{
        return newLocalSource.getFavNewsWithTitle(title)
    }

}