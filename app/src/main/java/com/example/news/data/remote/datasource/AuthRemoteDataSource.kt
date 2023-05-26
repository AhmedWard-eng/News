package com.example.news.data.remote.datasource

import com.example.news.data.remote.entity.AuthResponse
import com.example.news.domin.model.User

interface AuthRemoteDataSource {
    suspend fun login(email:String,password:String) : Result<RemoteUser>
    suspend fun signUP(email: String,userName:String,password: String) : AuthResponse

}