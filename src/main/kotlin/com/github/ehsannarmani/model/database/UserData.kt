package com.github.ehsannarmani.model.database

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class UserData<T>(
    @SerialName("user")
    val user:Long,
    @SerialName("key")
    val key:String,
    @SerialName("data")
    val data:T
)
