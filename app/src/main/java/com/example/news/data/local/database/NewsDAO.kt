package com.example.news.data.local.database

import androidx.room.*
import com.example.news.data.local.entity.FavNews
import com.example.news.data.local.entity.LocalNews
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(localNews: LocalNews)
    @Query("Select * from Articles")
    fun getAllNews(): Flow<List<LocalNews>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(localNewsList: List<LocalNews>)
    @Delete
    suspend fun deleteNews(localNews: LocalNews)

    @Query("Select * from FavoriteArticles where title = :title LIMIT 1")
    fun getFavNewsWithTitle(title: String): Flow<LocalNews>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavNews(favNews: FavNews)
    @Query("Select * from Articles")
    fun getAllFavNews(): Flow<List<FavNews>>
    @Delete
    suspend fun deleteFavNews(favNews: FavNews)
}