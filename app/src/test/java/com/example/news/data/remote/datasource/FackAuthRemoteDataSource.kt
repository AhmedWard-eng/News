package com.example.news.data.remote.datasource

class FackAuthRemoteDataSource : AuthRemoteDataSource {

    var email = "email"
    var password = "password"
    var userName = "userName"
    var id = "id"

    override suspend fun login(email: String, password: String): RemoteUser? {
        return if (email == this.email && password == this.password){
            RemoteUser(id, userName ,email , true)
        }else{
            null
        }
    }

    override suspend fun signUP(email: String, userName: String, password: String): Boolean {
        this.email = email
        this.userName = userName
        this.password = password
        return  true
    }

    override suspend fun logout(): Boolean {
        this.email = ""
        this.password = ""
        this.userName = ""
        this.id = ""

        return true
    }
}