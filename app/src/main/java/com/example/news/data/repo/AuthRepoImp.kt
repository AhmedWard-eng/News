package com.example.news.data.repo

import com.example.news.data.local.preferences.LocalUser
import com.example.news.data.local.preferences.PreferencesData
import com.example.news.data.local.preferences.UserManager
import com.example.news.data.remote.datasource.AuthRemoteDataSource

import com.example.news.data.remote.datasource.AuthRemoteDataSourceImp

class AuthRepoImp(
    private val authRemoteDataSource: AuthRemoteDataSource = AuthRemoteDataSourceImp(),
    private val userManager: UserManager = PreferencesData(),
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

    override suspend fun signUP(email: String, userName: String, password: String) : Boolean{
        return try {
            authRemoteDataSource.signUP(email, userName, password)
            true
        }catch (e: Exception){
            false
        }
    }

    override suspend fun logout(): Boolean {
        TODO()
    }

    override suspend fun saveLoggedInData(localUser: LocalUser) {
        userManager.saveUserData(localUser)
    }

}