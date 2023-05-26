package com.example.news.data.local.preferences

import android.content.SharedPreferences


private const val userId = "userId"
private const val name = "name"
private const val email = "email"


class PreferencesData(private val sharedPreferences: SharedPreferences = SharedPref.sharedPreferences) :
    UserManager {


    private val editor = sharedPreferences?.edit()
    override fun saveUserData(localUser: LocalUser) {
        editor?.putString(userId, localUser.userId)
        editor?.putString(name, localUser.userName)
        editor?.putString(email, localUser.email)
        editor?.apply()
    }

    override fun getUserData(): Result<LocalUser> {
        val userId = sharedPreferences.getString(userId, "")
        val name = sharedPreferences.getString(name, "")
        val email = sharedPreferences.getString(email, "")
        return if (userId.isNullOrBlank() || name.isNullOrBlank() || email.isNullOrBlank()) {
            Result.failure(Exception("User not Found"))
        } else {
            Result.success(
                LocalUser(
                    userId = userId,
                    userName = name,
                    email = email
                )
            )
        }
    }


}