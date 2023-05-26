package com.example.news.data.remote.datasource

import com.example.news.data.remote.entity.AuthRequest
import com.example.news.data.remote.entity.AuthResponse
import com.example.news.data.remote.network.RetrofitAPI
import com.example.news.data.remote.network.RetrofitService

class AuthRemoteDataSourceImp(private val retrofitService: RetrofitService = RetrofitAPI.apiService): AuthRemoteDataSource {
    override suspend fun login(email: String, password: String): RemoteUser? {
        TODO("Not yet implemented")
    }

    override suspend fun signUP(authRequest: AuthRequest) : AuthResponse {

        return retrofitService.signUp(authRequest)
    }

    override suspend fun logout(): Boolean {
        TODO("Not yet implemented")
    }
}