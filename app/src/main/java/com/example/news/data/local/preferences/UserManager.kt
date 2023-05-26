package com.example.news.data.local.preferences

interface UserManager {
    fun saveUserData(localUser: LocalUser)
    fun getUserData() : Result<LocalUser>
}