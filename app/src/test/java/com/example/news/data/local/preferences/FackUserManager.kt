package com.example.news.data.local.preferences

class FackUserManager : UserManager {

    lateinit var email : String
    lateinit var userId : String
    lateinit var userName : String

    override fun saveUserData(localUser: LocalUser) {
        email = localUser.email
        userId = localUser.userId
        userName = localUser.userName
    }
}