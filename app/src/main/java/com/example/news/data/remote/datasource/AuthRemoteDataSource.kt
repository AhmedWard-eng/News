package com.example.news.data.remote.datasource

import com.example.news.data.remote.entity.AuthResponse

interface AuthRemoteDataSource {
    fun login(email:String,password:String)
    suspend fun  signUP(email: String,userName:String,password: String): AuthResponse
}