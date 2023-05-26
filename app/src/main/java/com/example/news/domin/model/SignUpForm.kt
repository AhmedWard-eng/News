package com.example.news.domin.model

import com.example.news.data.remote.entity.SignupRequest

data class SignUpForm(val password: String, val userName: String, val email: String)

fun SignUpForm.toAuthRequst(): SignupRequest {
    return SignupRequest(displayName = userName, email = email, password = password)
}