package com.example.news.data.remote.datasource

import com.example.news.data.remote.entity.SignupResponse

interface AuthRemoteDataSource {
    suspend fun login(email:String,password:String) : RemoteUser?
    suspend fun signUP(email: String,userName:String,password: String) : SignupResponse

    fun isUserAuth() : Boolean

}