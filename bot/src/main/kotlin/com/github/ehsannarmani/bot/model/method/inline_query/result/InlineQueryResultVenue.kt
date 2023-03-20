package com.github.ehsannarmani.bot.model.method.inline_query.result

import com.github.ehsannarmani.bot.model.message.keyboard.InlineKeyboard
import com.github.ehsannarmani.bot.model.method.inline_query.InputMessageContent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID


@Serializable
data class InlineQueryResultVenue(
    @SerialName("type")
    val type:String = "venue",
    @SerialName("id")
    val id:String = UUID.randomUUID().toString(),
    @SerialName("title")
    val title:String,
    @SerialName("latitude")
    val latitude:Double,
    @SerialName("longitude")
    val longitude:Double,
    @SerialName("address")
    val address:String,
    @SerialName("foursquare_id")
    val foursquareId:String,
    @SerialName("foursquare_type")
    val foursquareType:String,
    @SerialName("google_place_id")
    val googlePlaceId:String,
    @SerialName("google_place_type")
    val googlePlaceType:String,
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
