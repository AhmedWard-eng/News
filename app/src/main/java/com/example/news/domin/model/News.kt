package com.example.news.domin.model

import android.os.Parcelable
import com.example.news.data.local.entity.LocalNews
import kotlinx.android.parcel.Parcelize


@Parcelize
data class News(
    val author: String = "",
    val title: String = "",
    val description: String = "",
    val url: String = "",
    val urlToImage: String = "",
    val publishedAt: String = "",
    val content: String = "",
    val isFav : Boolean = false
): Parcelable

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
