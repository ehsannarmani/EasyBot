package com.github.ehsannarmani.model.method.inline_query.result

import com.github.ehsannarmani.model.message.MessageEntity
import com.github.ehsannarmani.model.message.keyboard.InlineKeyboard
import com.github.ehsannarmani.model.message.keyboard.Keyboard
import com.github.ehsannarmani.model.method.inline_query.InputMessageContent
import com.github.ehsannarmani.model.method.inline_query.result.InlineQueryResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID


@Serializable
data class InlineQueryResultLocation(
    @SerialName("type")
    val type:String = "location",
    @SerialName("id")
    val id:String = UUID.randomUUID().toString(),
    @SerialName("title")
    val title:String,
    @SerialName("latitude")
    val latitude:Double,
    @SerialName("longitude")
    val longitude:Double,
    @SerialName("horizontal_accuracy")
    val horizontalAccuracy:Double? = null,
    @SerialName("live_period")
    val livePeriod:Int? = null,
    @SerialName("heading")
    val heading:Int? = null,
    @SerialName("proximity_alert_radius")
    val proximityAlertRadius:Int? = null,
    @SerialName("thumbnail_url")
    val thumbnailUrl:String? = null,
    @SerialName("thumbnail_width")
    val thumbnailWidth:Int? = null,
    @SerialName("thumbnail_height")
    val thumbnailHeight:Int? = null,
    @SerialName("reply_markup")
    val keyboard: InlineKeyboard? = null,
    @SerialName("input_message_content")
    val inputMessageContent:InputMessageContent? = null
): InlineQueryResult()
