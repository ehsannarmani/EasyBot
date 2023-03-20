package com.github.ehsannarmani.bot.model.method

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatSenderChat(
    @SerialName("chat_id")
    val chatId:String,
    @SerialName("sender_chat_id")
    val senderChatId:Long
)
