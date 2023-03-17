package com.github.ehsannarmani.model.updating_messages

import com.github.ehsannarmani.model.message.keyboard.Keyboard
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class EditTextMessage(
    @SerialName("text")
    val text: String,
    @SerialName("chat_id")
    val chatId: String,
    @SerialName("message_id")
    val messageId: Int? = null,
    @SerialName("inline_message_id")
    val inlineMessageId: Int? = null,
    @SerialName("parse_mode")
    val parseMode: String = "markdown",
    @SerialName("disable_web_page_preview")
    val disableWebPagePreview: Boolean = false,
    @SerialName("message_thread_id")
    val messageThreadId: Long? = null,
    @SerialName("reply_markup")
    val keyboard: Keyboard? = null
)
