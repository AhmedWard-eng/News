package com.example.news.data.local.database

import android.content.Context
import androidx.room.*
import com.example.news.data.local.entity.FavNews
import com.example.news.data.local.entity.LocalNews

@Database(entities = [LocalNews::class,FavNews::class], version = 1)
@TypeConverters(TypeConverter::class)
abstract class NewsDatabase:RoomDatabase()  {
abstract fun getNewsDAO() : NewsDAO
    companion object {
        lateinit var context: Context
        @Volatile
        private var INSTANCE: NewsDatabase? = null
        fun getInstance(): NewsDatabase {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                NewsDatabase::class.java,
                "NewsDatabase"
            )
                .fallbackToDestructiveMigration()
                .build()
            INSTANCE = instance
            return instance
        }
    }
}