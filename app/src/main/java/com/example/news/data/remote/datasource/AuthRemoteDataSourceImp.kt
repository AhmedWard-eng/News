package com.example.news.data.remote.datasource

import com.example.news.data.remote.entity.AuthRequest
import com.example.news.data.remote.entity.AuthRequestLogIn
import com.example.news.data.remote.entity.AuthResponse
import com.example.news.data.remote.network.RetrofitAPI
import com.example.news.data.remote.network.RetrofitService
import java.lang.Error
import kotlin.jvm.Throws

class AuthRemoteDataSourceImp(private val retrofitService: RetrofitService = RetrofitAPI.apiService): AuthRemoteDataSource {
    override suspend fun login(email: String, password: String): Result<RemoteUser> {
        val request = AuthRequestLogIn(email, password)
        val response =  retrofitService.loginIn(request)
        return if (response.error != null){
            Result.failure(Throwable(response.error.message))
        }else{
            Result.success(response)
        }
    }

    override suspend fun signUP(email: String, userName: String, password: String) : AuthResponse {
        val request = AuthRequest(userName,email,password)
        return retrofitService.signUp(request)
    }

    override suspend fun logout(): Boolean {
        TODO("Not yet implemented")
    }
}