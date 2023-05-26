package com.example.news.data.remote.datasource

import com.example.news.data.remote.entity.AuthRequest
import com.example.news.data.remote.entity.AuthResponse

class Auth : AuthRemoteDataSource {
    override suspend fun login(email: String, password: String): RemoteUser {
        TODO("Not yet implemented")
    }

    override suspend fun signUP(authRequest: AuthRequest): AuthResponse {
        TODO("Not yet implemented")
    }

    override suspend fun logout(): Boolean {
        TODO("Not yet implemented")
    }
}