package com.example.news.data.remote.network

import com.example.news.BuildConfig
import com.example.news.data.remote.entity.SignupRequest
import com.example.news.data.remote.entity.SignupResponse

import retrofit2.http.Body

import retrofit2.http.POST
import retrofit2.http.Query


private const val KEY = BuildConfig.API_KEY
interface RetrofitService {
    @POST("./accounts:signUp")

    suspend fun signUp(@Body signupRequest: SignupRequest, @Query("key") key : String = KEY) : SignupResponse
}