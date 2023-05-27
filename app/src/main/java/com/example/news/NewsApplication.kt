package com.example.news

import android.app.Application
import com.example.news.data.local.database.NewsDAO
import com.example.news.data.local.database.NewsDatabase
import com.example.news.data.local.preferences.SharedPref

class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPref.context = this
        NewsDatabase.context=this
    }
}