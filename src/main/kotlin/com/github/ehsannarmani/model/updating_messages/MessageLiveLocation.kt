package com.github.ehsannarmani.model.updating_messages

import com.github.ehsannarmani.model.message.MessageEntity
import com.github.ehsannarmani.model.message.keyboard.Keyboard
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MessageLiveLocation(
    @SerialName("chat_id")
    val chatId: String,
    @SerialName("message_id")
    val messageId: Int? = null,
    @SerialName("inline_message_id")
    val inlineMessageId: Int? = null,
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("horizontal_accuracy")
    val horizontalAccuracy: Double? = null,
    @SerialName("heading")
    val heading: Int? = null,
    @SerialName("proximity_alert_radius")
    val proximityAlertRadius: Int? = null,
    @SerialName("reply_markup")
    val keyboard: Keyboard? = null
)
