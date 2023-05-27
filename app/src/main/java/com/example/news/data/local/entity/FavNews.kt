package com.example.news.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteArticles")
data class FavNews(
    @PrimaryKey
    val title: String
)
