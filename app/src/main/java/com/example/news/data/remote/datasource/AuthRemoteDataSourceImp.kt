package com.example.news.data.remote.datasource

import com.example.news.data.remote.entity.SignupRequest
import com.example.news.data.remote.entity.SignupResponse
import com.example.news.data.remote.network.RetrofitAPI
import com.example.news.data.remote.network.RetrofitService

class AuthRemoteDataSourceImp(private val retrofitService: RetrofitService = RetrofitAPI.apiService): AuthRemoteDataSource {
    override suspend fun login(email: String, password: String): RemoteUser? {
        TODO("Not yet implemented")
    }

    override suspend fun signUP(email: String, userName: String, password: String) : SignupResponse {
        val request = SignupRequest(userName,email,password)
        return retrofitService.signUp(request)
    }

}