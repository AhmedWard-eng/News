package com.example.news.data.remote.datasource

import com.example.news.data.remote.entity.ErrorBody
import com.example.news.domin.model.User
import com.google.gson.annotations.SerializedName

data class RemoteUser(
    @SerializedName("localId")
    val userId: String?,
    @SerializedName("displayName")
    val userName: String?,
    val email: String?,
    val token: Boolean?,
    val error: ErrorBody?,
)

fun RemoteUser.mapRemoteUserToUser(): User {
    return User(this.userId ?: "", this.userName ?: "", this.email ?: "")
}