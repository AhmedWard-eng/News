package com.example.news.data.remote.network.news

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private  const val  newsUrl = "https://newsapi.org/v2/"

object RetrofitNewsApi {

    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(newsUrl)
        .addConverterFactory(GsonConverterFactory.create()).build()

    val apiService = retrofit.create(RetrofitNewsApi::class.java)
}