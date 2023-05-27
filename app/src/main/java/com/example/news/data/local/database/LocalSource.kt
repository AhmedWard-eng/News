package com.example.news.data.local.database

import com.example.news.data.local.entity.FavNews
import com.example.news.data.local.entity.LocalNews
import kotlinx.coroutines.flow.Flow

interface LocalSource {
    suspend fun insertNews(localNews: LocalNews)


    suspend fun insertNews(localNewsList: List<LocalNews>)
    fun getAllNews(): Flow<List<LocalNews>>
    suspend fun deleteNews(localNews: LocalNews)
    suspend fun getFavNewsWithTitle(title: String): FavNews?
    suspend fun insertFavNews(favNews: FavNews)
    fun getAllFavNews(): Flow<List<FavNews>>
    suspend fun deleteFavNews(title: String)
}