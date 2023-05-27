package com.example.news.data.remote.datasource.news

import com.example.news.data.remote.entity.news.RemoteNews
import com.example.news.data.remote.network.news.RetrofitNewsApi
import com.example.news.data.remote.network.news.RetrofitNewsService

class NewsRemoteDataImp(private val retrofitNewsService: RetrofitNewsService = RetrofitNewsApi.apiService) : NewsRemoteData {

    override suspend fun getNews(): List<RemoteNews> {
        val response = retrofitNewsService.getAllNews()
        if(response.error != null){
            throw Exception(response.error.message)
        }else{
            return response.articles
        }
    }
}