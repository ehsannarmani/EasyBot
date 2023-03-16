package com.github.ehsannarmani.model.message

import com.github.ehsannarmani.model.message.keyboard.Keyboard
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContactMessage(
    @SerialName("chat_id")
    val chatId:String,
    @SerialName("message_thread_id")
    val messageThreadId:Int? = null,
    @SerialName("phone_number")
    val phoneNumber:String,
    @SerialName("first_name")
    val firstName:String,
    @SerialName("last_name")
    val lastName:String? = null,
    @SerialName("vcard")
    val vcard:String? = null,
    @SerialName("disable_notification")
    val disableNotification: Boolean = false,
    @SerialName("protect_content")
    val protectContent: Boolean = false,
    @SerialName("reply_to_message_id")
    val replyToMessageId: Int? = null,
    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,
    @SerialName("reply_markup")
    val keyboard: Keyboard? = null
)
