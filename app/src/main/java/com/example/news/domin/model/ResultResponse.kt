package com.example.news.domin.model

import com.example.news.data.remote.entity.AuthResponse
import com.example.news.data.remote.entity.SignupResponse

data class SignUpResult(val email: String? , val localId: String?)

fun SignupResponse.signUpResult(): SignUpResult {
    return SignUpResult(email = email , localId = localId)
}
