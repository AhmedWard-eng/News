package com.example.news.data.remote.entity

import com.example.news.data.remote.entity.news.RemoteNews
import com.example.news.domin.model.News


fun RemoteNews.toNews() : News{
    return News(title)
}