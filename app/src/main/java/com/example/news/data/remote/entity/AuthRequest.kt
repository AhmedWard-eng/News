package com.example.news.data.remote.entity

data class AuthRequest(
    val displayName: String,
    val email: String,
    val password: String
)