package com.github.ehsannarmani.bot.model.method

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatAdminCustomTitle(
    @SerialName("chat_id")
    val chatId:String,
    @SerialName("user_id")
    val userId:Long,
    @SerialName("custom_title")
    val customTitle:String,
)
