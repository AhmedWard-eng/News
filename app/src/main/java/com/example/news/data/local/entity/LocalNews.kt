package com.example.news.data.local.entity

import com.example.news.domin.model.News

data class LocalNews(val title:String)

fun LocalNews.toNews():News{
    return News(title = title)
}