package com.example.news.data.remote.entity

import com.example.news.data.local.entity.LocalNews
import com.example.news.domin.model.News

data class RemoteNews(val title: String)

fun RemoteNews.toNews(): News {
    return News("", title, "", "", "", "", "")
}

fun RemoteNews.toLocalNews(): LocalNews {
    return LocalNews("", title, "", "", "", "", "")
}