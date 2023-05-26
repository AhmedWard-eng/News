package com.example.news.data.remote.entity

data class ErrorX(
    val code: Int,
    val errors: List<ErrorXX>,
    val message: String
)