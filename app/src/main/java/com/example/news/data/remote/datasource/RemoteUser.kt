package com.example.news.data.remote.datasource

import com.example.news.domin.model.User
import java.lang.Error

data class RemoteUser(
    val userId: String?,
    val userName: String?,
    val email: String?,
    val token: Boolean?,
    val error : Error?
)

fun RemoteUser.mapRemoteUserToUser(): User {
    return User(this.userId ?: "" , this.userName ?: "", this.email ?: "")
}