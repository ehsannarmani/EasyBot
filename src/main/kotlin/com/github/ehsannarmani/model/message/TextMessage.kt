package com.github.ehsannarmani.model.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TextMessage(
    @SerialName("text")
    val text: String,
    @SerialName("chat_id")
    val chatId: Long,
    val parseMode: String = "markdown",
    val disableWebPagePreview: Boolean = false,
    val disableNotification: Boolean = false,
    val messageThreadId: Long? = null,
    val protectContent: Boolean = false,
    val replyToMessageId: Int? = null,
    val allowSendingWithoutReply: Boolean? = null,
    val replyMarkup: String? = null
)
