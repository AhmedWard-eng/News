package com.example.news.data.repo

import com.example.news.data.local.preferences.LocalUser
import com.example.news.data.local.preferences.PreferncesData
import com.example.news.data.local.preferences.UserManager
import com.example.news.data.remote.datasource.Auth
import com.example.news.data.remote.datasource.AuthRemoteDataSource
import com.example.news.data.remote.entity.AuthResponse
import com.example.news.domin.model.SignUpForm
import com.example.news.domin.model.SignUpResult
import com.example.news.domin.model.signUpResult
import com.example.news.domin.model.toAuthRequst

class AuthRepoImp(
    private val authRemoteDataSource: AuthRemoteDataSource = Auth(),
    private val userManager: UserManager = PreferncesData(),
) : AuthRepo {

    override suspend fun login(email: String, password: String): Boolean {
        val user = authRemoteDataSource.login(email, password)

        if (user != null) {
            saveLoggedInData(LocalUser(user.userId, user.userName, user.email))
            return true
        }

        return false
    }

    override suspend fun signUP(signUpForm: SignUpForm): SignUpResult {
        val result =  authRemoteDataSource.signUP(signUpForm.toAuthRequst())
        return result.signUpResult()
    }

    override suspend fun logout(): Boolean {
        return authRemoteDataSource.logout()
    }

    override suspend fun saveLoggedInData(localUser: LocalUser) {
        userManager.saveUserData(localUser)
    }

}