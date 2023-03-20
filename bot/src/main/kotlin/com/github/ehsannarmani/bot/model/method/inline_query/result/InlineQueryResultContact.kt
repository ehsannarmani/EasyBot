package com.github.ehsannarmani.bot.model.method.inline_query.result

import com.github.ehsannarmani.bot.model.message.keyboard.InlineKeyboard
import com.github.ehsannarmani.bot.model.method.inline_query.InputMessageContent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID


@Serializable
data class InlineQueryResultContact(
    @SerialName("type")
    val type:String = "contact",
    @SerialName("id")
    val id:String = UUID.randomUUID().toString(),
    @SerialName("phone_number")
    val phoneNumber:String,
    @SerialName("first_name")
    val firstName:String,
    @SerialName("last_name")
    val lastName:String? = null,
    @SerialName("vcard")
    val vcard:String? = null,
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
