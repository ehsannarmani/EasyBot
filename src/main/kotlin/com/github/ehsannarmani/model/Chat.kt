package com.github.ehsannarmani.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Chat(
    @SerialName("id")
    val id:Int,
    @SerialName("first_name")
    val firstName:String?,
    @SerialName("last_name")
    val lastName:String? = null,
    @SerialName("username")
    val username:String? = null,
    @SerialName("type")
    val type:String
)
