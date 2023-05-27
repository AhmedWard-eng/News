package com.example.news.data.remote.datasource

import com.example.news.data.remote.entity.ErrorBody
import com.example.news.domin.model.User

data class RemoteUser(
    val userId: String?,
    val userName: String?,
    val email: String?,
    val token: Boolean?,
    val error : ErrorBody?
)

fun RemoteUser.mapRemoteUserToUser(): User {
    return User(this.userId ?: "" , this.userName ?: "", this.email ?: "")
}