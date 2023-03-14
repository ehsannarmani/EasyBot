package com.github.ehsannarmani.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatMember(
    @SerialName("user")
    val user:From,
    @SerialName("status")
    val status:String,
)
