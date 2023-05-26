package com.example.news.data.remote.datasource

import com.example.news.data.remote.entity.SignupResponse

import com.example.news.data.remote.entity.AuthRequest
import com.example.news.data.remote.entity.AuthResponse


interface AuthRemoteDataSource {
    suspend fun login(email:String,password:String) : RemoteUser?
    suspend fun signUP(authRequest: AuthRequest) : SignupResponse

}