package com.example.news.data.remote.entity

data class SignupResponse(
    val email: String,
    val expiresIn: String,
    val idToken: String,
    val kind: String,
    val localId: String,
    val refreshToken: String
)