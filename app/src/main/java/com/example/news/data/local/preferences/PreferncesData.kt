package com.example.news.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.preferences.preferencesDataStore


private const val userId = "userId"
private const val  name = "name"
private const val email = "email"


class PreferencesData(private val sharedPreferences: SharedPreferences = SharedPref.sharedPreferences) : UserManager {


    private val editor = sharedPreferences?.edit()
    override fun saveUserData(localUser: LocalUser) {
        editor?.putString(userId,localUser.userId)
        editor?.putString(name, localUser.name)
        editor?.putString(email,localUser.name)
        editor?.apply()
    }

    override fun getUserData() : Result<LocalUser>{
        val userId =  sharedPreferences.getString(userId,"")
        val name = sharedPreferences.getString(name,"")
        val email = sharedPreferences.getString(email,"")
        if(userId != null && name != null && email != null){
            return Result.success(LocalUser(
                userId = userId,
                name = name,
                email = email
            ))
        }else{
            return Result.failure(Exception("User not Found"))
        }

    }


}