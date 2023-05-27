package com.example.news.domin.model

import com.example.news.data.local.entity.LocalNews


data class News(
    val author: String = "",
    val title: String = "",
    val description: String = "",
    val url: String = "",
    val urlToImage: String = "",
    val publishedAt: String = "",
    val content: String = "",
    val isFav : Boolean = false
)

fun News.toLocalNews(): LocalNews {
    return LocalNews(
        title = title,
        author = author,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}
