package com.github.ehsannarmani.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class From(
    @SerialName("id")
    val id:Int,
    @SerialName("is_bot")
    val isBot:Boolean,
    @SerialName("first_name",)
    val firstName:String,
    @SerialName("last_name")
    val lastName:String? = null,
    @SerialName("username")
    val username:String? = null,
    @SerialName("language_code")
    val languageCode:String
)
