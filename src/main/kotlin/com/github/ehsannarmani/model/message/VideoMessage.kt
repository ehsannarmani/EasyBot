package com.github.ehsannarmani.model.message

import com.github.ehsannarmani.model.message.keyboard.Keyboard
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class VideoMessage(
    @SerialName("video")
    val video: String,
    @SerialName("chat_id")
    val chatId: String,
    @SerialName("caption")
    val caption: String? = null,
    @SerialName("parse_mode")
    val parseMode: String = "markdown",
    @SerialName("duration")
    val duration: Int? = null,
    @SerialName("width")
    val width: Int? = null,
    @SerialName("height")
    val height: Int? = null,
    @SerialName("has_spoiler")
    val hasSpoiler: Boolean? = null,
    @SerialName("support_streaming")
    val supportStreaming: Boolean? = null,
    @SerialName("thumbnail")
    val thumbnail: String? = null,
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,
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
