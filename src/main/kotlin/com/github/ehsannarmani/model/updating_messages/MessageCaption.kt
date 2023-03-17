package com.github.ehsannarmani.model.updating_messages

import com.github.ehsannarmani.model.message.MessageEntity
import com.github.ehsannarmani.model.message.keyboard.Keyboard
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MessageCaption(
    @SerialName("chat_id")
    val chatId: String,
    @SerialName("message_id")
    val messageId: Int? = null,
    @SerialName("inline_message_id")
    val inlineMessageId: Int? = null,
    @SerialName("caption")
    val caption: String? = null,
    @SerialName("parse_mode")
    val parseMode: String = "markdown",
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,
    @SerialName("reply_markup")
    val keyboard: Keyboard? = null
)
