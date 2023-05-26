package com.example.news.data.local.preferences

import android.app.Application
import android.content.Context
import android.content.SharedPreferences


private const val USER_PREF = "USER_PREF"
object SharedPref {
    lateinit var context : Application
    val sharedPreferences: SharedPreferences
        get() = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE)
}