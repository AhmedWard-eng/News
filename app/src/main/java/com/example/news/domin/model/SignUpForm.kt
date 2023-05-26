package com.example.news.domin.model

import com.example.news.data.local.entity.LocalNews
import com.example.news.data.remote.entity.AuthRequest
import com.example.news.data.remote.entity.AuthResponse

data class SignUpForm(val password: String, val userName: String, val email: String)

fun SignUpForm.toAuthRequst(): AuthRequest {
    return AuthRequest(displayName = userName, email = email, password = password)
}