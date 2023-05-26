package com.example.news.data.remote.datasource

interface AuthRemoteDataSource {
    fun login(email:String,password:String)
    fun signUP(email: String,userName:String,password: String)
    fun logout()
}