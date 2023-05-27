package com.example.news.data.remote.datasource

import com.example.news.data.remote.entity.ErrorBody
import com.example.news.domin.model.User

data class RemoteUser(
    val localId: String?,
    val displayName: String?,
    val email: String?,
    val token: Boolean?,
    val error : ErrorBody?
)

fun RemoteUser.mapRemoteUserToUser(): User {
    return User(this.localId ?: "" , this.displayName ?: "", this.email ?: "")
}