package com.example.news.data.remote.datasource

import android.util.Log
import com.example.news.data.local.preferences.PreferencesData
import com.example.news.data.local.preferences.UserManager
import com.example.news.data.remote.entity.SignupRequest
import com.example.news.data.remote.entity.AuthRequestLogIn
import com.example.news.data.remote.entity.ResponseError
import com.example.news.data.remote.entity.SignupResponse
import com.example.news.data.remote.network.RetrofitAPI
import com.example.news.data.remote.network.RetrofitService
import com.google.gson.Gson
import retrofit2.HttpException

class AuthRemoteDataSourceImp(
    private val retrofitService: RetrofitService = RetrofitAPI.apiService,
    private val preferencesData: UserManager = PreferencesData(),
) : AuthRemoteDataSource {
    override suspend fun login(email: String, password: String): Result<RemoteUser> {
        return try {
            val request = AuthRequestLogIn(email, password)
            val response = retrofitService.loginIn(request)
            Result.success(response)

        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val gson = Gson()
            val error = gson.fromJson(errorBody, ResponseError::class.java)
            Result.failure(Throwable(error.error.message))

        }
    }

    override suspend fun signUP(authRequest: SignupRequest): SignupResponse {
        return try {
            val response = retrofitService.signUp(authRequest)
            response
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val gson = Gson()
            val error = gson.fromJson(errorBody, ResponseError::class.java)
            throw Exception(error.error.message)
        }
    }

    override fun isUserAuth(): Boolean {
        return preferencesData.getUserData().isSuccess
    }


}