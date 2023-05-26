package com.example.news.data.remote.datasource

import com.example.news.data.remote.entity.SignupResponse
import com.example.news.data.remote.entity.SignupRequest


interface AuthRemoteDataSource {
    suspend fun signUP(authRequest: SignupRequest) : SignupResponse

    fun isUserAuth() : Boolean
    suspend fun login(email:String,password:String) : Result<RemoteUser>

}