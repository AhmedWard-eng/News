package com.example.news.data.remote.datasource

import com.example.news.data.remote.entity.SignupRequest
import com.example.news.data.remote.entity.AuthRequestLogIn
import com.example.news.data.remote.entity.SignupResponse
import com.example.news.data.remote.network.RetrofitAPI
import com.example.news.data.remote.network.RetrofitService

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

    override suspend fun signUP(email: String, userName: String, password: String) : SignupResponse {
        val request = SignupRequest(userName,email,password)
            val response = retrofitService.signUp(request)
            if(response.error != null){
                throw Exception(response.error.message)
            }else{
                return response
            }
    }

}