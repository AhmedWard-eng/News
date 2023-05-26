package com.example.news.data.repo

import com.example.news.data.local.preferences.LocalUser
import com.example.news.data.remote.entity.AuthResponse
import com.example.news.domin.model.User

interface AuthRepo {
    suspend fun login(email:String,password:String) : Result<User>
    suspend fun signUP(email: String,userName:String,password: String) : AuthResponse
    suspend fun logout() : Boolean
    suspend fun saveLoggedInData(localUser : LocalUser)
}