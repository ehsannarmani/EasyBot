package com.github.ehsannarmani.model.message

import com.github.ehsannarmani.model.message.keyboard.Keyboard
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LocationMessage(
    @SerialName("chat_id")
    val chatId:String,
    @SerialName("message_thread_id")
    val messageThreadId:Int? = null,
    @SerialName("latitude")
    val latitude:Float,
    @SerialName("longitude")
    val longitude:Float,
    @SerialName("horizontal_accuracy")
    val horizontalAccuracy:Float? = null,
    @SerialName("live_period")
    val livePeriod:Int? = null,
    @SerialName("heading")
    val heading:Int? = null,
    @SerialName("proximity_alert_radius")
    val proximityAlertRadius:Int? = null,
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
