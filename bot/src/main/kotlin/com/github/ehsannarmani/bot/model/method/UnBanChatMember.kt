package com.github.ehsannarmani.bot.model.method

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UnBanChatMember(
    @SerialName("chat_id")
    val chatId:String,
    @SerialName("user_id")
    val userId:Long,
    @SerialName("only_if_banned")
    val onlyIfBanned:Boolean? = null,
)
