package com.github.ehsannarmani.model.message

import com.github.ehsannarmani.model.message.keyboard.Keyboard
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class VideoNoteMessage(
    @SerialName("video_note")
    val videoNote: String,
    @SerialName("chat_id")
    val chatId: String,
    @SerialName("duration")
    val duration: Int? = null,
    @SerialName("length")
    val length: Int? = null,
    @SerialName("thumbnail")
    val thumbnail: String? = null,
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
