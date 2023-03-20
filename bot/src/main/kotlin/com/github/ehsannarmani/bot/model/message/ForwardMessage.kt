package com.github.ehsannarmani.bot.model.message

import com.github.ehsannarmani.bot.model.message.keyboard.Keyboard
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ForwardMessage(
    @SerialName("chat_id")
    val chatId: String,
    @SerialName("from_chat_id")
    val fromChatId: String,
    @SerialName("message_id")
    val messageId: Int,
    @SerialName("disable_notification")
    val disableNotification: Boolean = false,
    @SerialName("message_thread_id")
    val messageThreadId: Long? = null,
    @SerialName("protect_content")
    val protectContent: Boolean = false,
)
