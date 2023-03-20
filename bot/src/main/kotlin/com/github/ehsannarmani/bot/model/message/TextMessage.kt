package com.github.ehsannarmani.bot.model.message

import com.github.ehsannarmani.bot.model.message.keyboard.Keyboard
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TextMessage(
    @SerialName("text")
    val text: String,
    @SerialName("chat_id")
    val chatId: String,
    @SerialName("parse_mode")
    val parseMode: String = "markdown",
    @SerialName("disable_web_page_preview")
    val disableWebPagePreview: Boolean = false,
    @SerialName("disable_notification")
    val disableNotification: Boolean = false,
    @SerialName("message_thread_id")
    val messageThreadId: Long? = null,
    @SerialName("protect_content")
    val protectContent: Boolean = false,
    @SerialName("reply_to_message_id")
    val replyToMessageId: Int? = null,
    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,
    @SerialName("reply_markup")
    val keyboard: Keyboard? = null
)
