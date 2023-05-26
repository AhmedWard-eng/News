package com.example.news.data.remote.datasource

interface AuthRemoteDataSource {
    suspend fun login(email:String,password:String) : RemoteUser?
    suspend fun signUP(email: String,userName:String,password: String) : Boolean
    suspend fun logout() : Boolean
}