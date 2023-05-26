package com.example.news.data.remote.datasource

import com.example.news.data.remote.entity.SignupRequest
import com.example.news.data.remote.entity.SignupResponse

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

    override suspend fun signUP(authRequest: SignupRequest) : SignupResponse {
        TODO("Not yet implemented")
    }

    override fun isUserAuth(): Boolean {
        TODO("Not yet implemented")
    }

}