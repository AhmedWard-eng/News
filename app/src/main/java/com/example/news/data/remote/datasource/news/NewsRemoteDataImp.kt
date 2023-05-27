package com.example.news.data.remote.datasource.news

import com.example.news.data.remote.entity.RemoteNews
import com.example.news.data.remote.network.news.RetrofitNewsApi

class NewsRemoteDataImp(private val retrofitNewsService: RetrofitNewsApi = RetrofitNewsApi.apiService) : NewsRemoteData {

    override suspend fun getNews(): List<RemoteNews> {
        TODO("Not yet implemented")
    }
}