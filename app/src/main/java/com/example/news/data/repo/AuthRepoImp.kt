package com.example.news.data.repo

import com.example.news.data.local.preferences.LocalUser
import com.example.news.data.local.preferences.PreferncesData
import com.example.news.data.local.preferences.UserManager
import com.example.news.data.remote.datasource.Auth
import com.example.news.data.remote.datasource.AuthRemoteDataSource
import com.example.news.data.remote.datasource.RemoteUser
import com.example.news.data.remote.datasource.mapRemoteUserToUser
import com.example.news.data.remote.entity.AuthRequest
import com.example.news.data.remote.entity.AuthResponse
import com.example.news.domin.model.User
import java.lang.IllegalArgumentException

class AuthRepoImp(
    private val authRemoteDataSource: AuthRemoteDataSource = Auth(),
    private val userManager: UserManager = PreferncesData(),
) : AuthRepo {

    override suspend fun login(email: String, password: String): Result<User> {
        val user = authRemoteDataSource.login(email, password)
        val result = user.getOrNull()
        if (user.isSuccess) {
                result?.let {
                    saveLoggedInData(LocalUser(it.userId ?: "", it.userName ?:"", it.email ?:""))
                    return Result.success(it.mapRemoteUserToUser())
                }
            return Result.failure(IllegalArgumentException())
        }else{

                return Result.failure(user.exceptionOrNull()!!)
        }
    }

    override suspend fun signUP(email: String, userName: String, password: String): AuthResponse {
        return authRemoteDataSource.signUP(email, userName, password)
    }

    override suspend fun logout(): Boolean {
        return authRemoteDataSource.logout()
    }

    override suspend fun saveLoggedInData(localUser: LocalUser) {
        userManager.saveUserData(localUser)
    }

}