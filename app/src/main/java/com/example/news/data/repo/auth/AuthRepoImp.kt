package com.example.news.data.repo.auth

import com.example.news.data.local.preferences.LocalUser
import com.example.news.data.local.preferences.PreferencesData
import com.example.news.data.local.preferences.UserManager
import com.example.news.data.remote.datasource.AuthRemoteDataSource

import com.example.news.data.remote.datasource.AuthRemoteDataSourceImp
import com.example.news.domin.model.SignUpForm
import com.example.news.domin.model.SignUpResult
import com.example.news.domin.model.signUpResult
import com.example.news.domin.model.toAuthRequst
import com.example.news.data.remote.datasource.mapRemoteUserToUser
import com.example.news.domin.model.User

class AuthRepoImp(
    private val authRemoteDataSource: AuthRemoteDataSource = AuthRemoteDataSourceImp(),
    private val userManager: UserManager = PreferencesData(),
) : AuthRepo {

    override suspend fun login(email: String, password: String): Result<User> {
        val user = authRemoteDataSource.login(email, password)
        val result = user.getOrNull()
        if (user.isSuccess) {
            result?.let {
                saveLoggedInData(LocalUser(it.userId ?: "", it.userName ?: "", it.email ?: ""))
                return Result.success(it.mapRemoteUserToUser())
            }
            return Result.failure(IllegalArgumentException())
        } else {
            return Result.failure(user.exceptionOrNull()!!)
        }
    }

    override suspend fun signUP(signUpForm: SignUpForm): Result<SignUpResult> {
        return try {
            val result = authRemoteDataSource.signUP(signUpForm.toAuthRequst())
            return Result.success(result.signUpResult())
        } catch (e: Exception) {
            Result.failure(Exception(e.message))
        }
    }

    override suspend fun logout(): Boolean {
        TODO()
    }

    override suspend fun saveLoggedInData(localUser: LocalUser) {
        userManager.saveUserData(localUser)
    }

}