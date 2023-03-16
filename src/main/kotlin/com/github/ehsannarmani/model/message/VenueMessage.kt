package com.github.ehsannarmani.model.message

import com.github.ehsannarmani.model.message.keyboard.Keyboard
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class VenueMessage(
    @SerialName("chat_id")
    val chatId:String,
    @SerialName("message_thread_id")
    val messageThreadId:Int? = null,
    @SerialName("latitude")
    val latitude:Float,
    @SerialName("longitude")
    val longitude:Float,
    @SerialName("title")
    val title:String,
    @SerialName("address")
    val address:String,
    @SerialName("foursquare_id")
    val foursquareId:String? = null,
    @SerialName("foursquare_type")
    val foursquareType:String? = null,
    @SerialName("google_place_id")
    val googlePlaceId:String? = null,
    @SerialName("google_place_type")
    val googlePlaceType:String? = null,
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
