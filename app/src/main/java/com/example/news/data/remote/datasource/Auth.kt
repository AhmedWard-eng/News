package com.example.news.data.remote.datasource

import com.example.news.data.repo.AuthRepoImp

class Auth : AuthRemoteDataSource {
    override suspend fun login(email: String, password: String): RemoteUser {
        TODO("Not yet implemented")
    }

    override suspend fun signUP(email: String, userName: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun logout(): Boolean {
        TODO("Not yet implemented")
    }
}