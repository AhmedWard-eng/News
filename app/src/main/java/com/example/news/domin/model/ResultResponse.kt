package com.example.news.domin.model

import com.example.news.data.remote.entity.AuthResponse

data class SignUpResult(val email: String , val localId: String)

fun AuthResponse.signUpResult(): SignUpResult {
    return SignUpResult(email = email , localId = localId)
}
