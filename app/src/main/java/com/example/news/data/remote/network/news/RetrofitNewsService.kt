package com.example.news.data.remote.network.news

import com.example.news.BuildConfig
import com.example.news.data.remote.entity.news.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val NewsKey = BuildConfig.API_NewsKey

interface RetrofitNewsService {
    @GET("top-headlines")
    suspend fun getAllNews(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = NewsKey
    ): NewsResponse
}