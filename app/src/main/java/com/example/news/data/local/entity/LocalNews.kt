package com.example.news.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.news.domin.model.News

@Entity(tableName = "Articles")
data class LocalNews(
    val author: String,
    @PrimaryKey
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)

fun LocalNews.toNews(isFav : Boolean = false): News {
    return News(
        title = title,
        author = author,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content,
        isFav =  isFav
    )
}