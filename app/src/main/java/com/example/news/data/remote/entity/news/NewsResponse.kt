package com.example.news.data.remote.entity.news

import com.example.news.data.remote.entity.ErrorBody
import com.google.gson.annotations.SerializedName


data class NewsResponse(
    val status: String,
    val totalResults: Int,
    @SerializedName("articles")
    val articles: List<RemoteNews>,
    val error : ErrorBody?
)