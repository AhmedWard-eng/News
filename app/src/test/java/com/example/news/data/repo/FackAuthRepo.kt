package com.example.news.data.repo

import com.example.news.data.local.preferences.LocalUser

class FackAuthRepo : AuthRepo {



    override suspend fun login(email: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun signUP(email: String, userName: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun logout(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun saveLoggedInData(localUser: LocalUser) {
        TODO("Not yet implemented")
    }
}