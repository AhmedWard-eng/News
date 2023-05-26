package com.example.news.data.remote.datasource

import com.example.news.data.remote.entity.SignupRequest
import com.example.news.data.remote.entity.SignupResponse
import com.example.news.data.remote.network.RetrofitAPI
import com.example.news.data.remote.network.RetrofitService
import java.lang.Exception

class AuthRemoteDataSourceImp(private val retrofitService: RetrofitService = RetrofitAPI.apiService): AuthRemoteDataSource {

    override suspend fun login(email: String, password: String): RemoteUser? {
        TODO("Not yet implemented")

    }

    override suspend fun signUP(authRequest: AuthRequest) : SignupResponse {
            val response = retrofitService.signUp(authRequest)
            if(response.error != null){
                throw Exception(response.error.message)
            }else{
                return response
            }
    }

}