package com.example.news.data.repo.auth

import com.example.news.data.local.preferences.LocalUser
import com.example.news.data.remote.entity.SignupResponse
import com.example.news.domin.model.SignUpForm
import com.example.news.domin.model.SignUpResult

import com.example.news.domin.model.User

interface AuthRepo {
    suspend fun login(email:String,password:String) : Result<User>
    suspend fun signUP(signUpForm: SignUpForm): Result<SignUpResult>
    suspend fun logout() : Boolean
    suspend fun saveLoggedInData(localUser : LocalUser)
}