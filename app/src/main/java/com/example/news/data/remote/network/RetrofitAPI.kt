package com.example.news.data.remote.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private  const val  url = "https://identitytoolkit.googleapis.com/v1/"
object RetrofitAPI {

    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create()).build()

    val apiService = retrofit.create(RetrofitService::class.java)

}