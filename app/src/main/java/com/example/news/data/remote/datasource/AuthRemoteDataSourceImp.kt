package com.example.news.data.remote.datasource

import com.example.news.data.local.preferences.PreferencesData
import com.example.news.data.local.preferences.UserManager
import com.example.news.data.remote.entity.SignupRequest
import com.example.news.data.remote.entity.AuthRequestLogIn
import com.example.news.data.remote.entity.SignupResponse
import com.example.news.data.remote.network.RetrofitAPI
import com.example.news.data.remote.network.RetrofitService

class AuthRemoteDataSourceImp(private val retrofitService: RetrofitService = RetrofitAPI.apiService,private val preferencesData: UserManager = PreferencesData()): AuthRemoteDataSource {
    override suspend fun login(email: String, password: String): Result<RemoteUser> {
        val request = AuthRequestLogIn(email, password)
        val response =  retrofitService.loginIn(request)
        return if (response.error != null){
            Result.failure(Throwable(response.error.message))
        }else{
            Result.success(response)
        }
    }

    override suspend fun signUP(authRequest: SignupRequest) : SignupResponse {
            val response = retrofitService.signUp(authRequest)
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