package com.example.news.data.remote.network

import com.example.news.data.remote.entity.AuthRequest
import com.example.news.data.remote.entity.AuthResponse

import retrofit2.http.Body

import retrofit2.http.POST
import retrofit2.http.Query


interface RetrofitService {
    @POST("/accounts:signInWithPassword")
    suspend fun signUp(@Body authRequest: AuthRequest, @Query("key") key : String = "AIzaSyAVRNpZKTubdM9mp3L9HaE8XGj09eNoV1o") : AuthResponse
}