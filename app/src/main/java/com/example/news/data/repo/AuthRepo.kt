package com.example.news.data.repo

interface AuthRepo {
    fun login(email:String,password:String)
    fun signUP(email: String,userName:String,password: String)
    fun logout()
}