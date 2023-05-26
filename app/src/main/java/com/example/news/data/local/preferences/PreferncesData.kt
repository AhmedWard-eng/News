package com.example.news.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.preferences.preferencesDataStore


const val USER_PREF = "USER_PREF"
const val userId = "userId"
const val  name = "name"
const val email = "email"


class PreferencesData(private val sharedPreferences: SharedPreferences? = SharedPref.sharedPreferences) : UserManager {


    private val editor = sharedPreferences?.edit()
    override fun saveUserData(localUser: LocalUser) {
        editor?.putString(userId,localUser.userId)
        editor?.putString(name, localUser.name)
        editor?.putString(email,localUser.name)
        editor?.apply()
    }

    override fun getUserData() : LocalUser{
        return LocalUser(
            userId = sharedPreferences?.getString(userId,"") ?: "",
            name = sharedPreferences?.getString(name,"") ?: "",
            email = sharedPreferences?.getString(email,"") ?: ""
        )
    }


}