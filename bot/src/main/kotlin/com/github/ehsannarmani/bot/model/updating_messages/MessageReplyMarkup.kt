package com.github.ehsannarmani.bot.model.updating_messages

import com.github.ehsannarmani.bot.model.message.keyboard.Keyboard
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MessageReplyMarkup(
    @SerialName("chat_id")
    val chatId: String,
    @SerialName("message_id")
    val messageId: Int? = null,
    @SerialName("inline_message_id")
    val inlineMessageId: Int? = null,
    @SerialName("reply_markup")
    val keyboard: Keyboard? = null
)
