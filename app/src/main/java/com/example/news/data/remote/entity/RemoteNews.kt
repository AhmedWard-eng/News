package com.example.news.data.remote.entity

import com.example.news.data.remote.entity.news.RemoteNews
import com.example.news.data.local.entity.LocalNews
import com.example.news.domin.model.News


fun RemoteNews.toNews(): News {
    return News( title = title?:"",
        author = author?:"",
        description = description?:"",
        url = url?:"",
        urlToImage = urlToImage?:"",
        publishedAt = publishedAt?:"",
        content = content?:"")
}

fun RemoteNews.toLocalNews(): LocalNews {
    return LocalNews(title = title?:"",
        author = author?:"",
        description = description?:"",
        url = url?:"",
        urlToImage = urlToImage?:"",
        publishedAt = publishedAt?:"",
        content = content?:"")
}