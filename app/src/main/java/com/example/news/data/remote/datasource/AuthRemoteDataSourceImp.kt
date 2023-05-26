package com.example.news.data.remote.datasource

import com.example.news.data.local.preferences.PreferencesData
import com.example.news.data.local.preferences.UserManager
import com.example.news.data.remote.entity.SignupRequest
import com.example.news.data.remote.entity.SignupResponse
import com.example.news.data.remote.network.RetrofitAPI
import com.example.news.data.remote.network.RetrofitService
import java.lang.Exception

class AuthRemoteDataSourceImp(private val retrofitService: RetrofitService = RetrofitAPI.apiService,private val preferencesData: UserManager = PreferencesData()): AuthRemoteDataSource {

    override suspend fun login(email: String, password: String): RemoteUser? {
        TODO("Not yet implemented")

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

    override fun isUserAuth(): Boolean {
        return preferencesData.getUserData().isSuccess
    }


}