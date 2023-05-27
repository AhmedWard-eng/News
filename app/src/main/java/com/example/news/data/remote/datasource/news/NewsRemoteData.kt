package com.example.news.data.remote.datasource.news

import com.example.news.data.remote.entity.RemoteNews

interface NewsRemoteData {
    suspend fun getNews() : List<RemoteNews>
}