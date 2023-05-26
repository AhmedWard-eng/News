package com.example.news.data.repo.auth

import com.example.news.data.local.preferences.LocalUser
import com.example.news.data.remote.entity.SignupResponse


interface AuthRepo {
    suspend fun login(email:String,password:String) : Boolean
    suspend fun signUP(email: String,userName:String,password: String): Result<Boolean>
    suspend fun logout() : Boolean
    suspend fun saveLoggedInData(localUser : LocalUser)
}