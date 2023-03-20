package com.github.ehsannarmani.bot.model.method.inline_query.result

import com.github.ehsannarmani.bot.model.message.MessageEntity
import com.github.ehsannarmani.bot.model.message.keyboard.InlineKeyboard
import com.github.ehsannarmani.bot.model.method.inline_query.InputMessageContent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID


@Serializable
data class InlineQueryResultGif(
    @SerialName("type")
    val type:String = "gif",
    @SerialName("id")
    val id:String = UUID.randomUUID().toString(),
    @SerialName("gif_url")
    val gifUrl:String,
    @SerialName("thumbnail_url")
    val thumbnailUrl:String,
    @SerialName("thumbnail_mime_type")
    val thumbnailMimeType:String? = null,
    @SerialName("gif_width")
    val gifWidth:Int? = null,
    @SerialName("gif_height")
    val gifHeight:Int? = null,
    @SerialName("gif_duration")
    val gifDuration:Int? = null,
    @SerialName("title")
    val title:String? = null,
    @SerialName("description")
    val description:String? = null,
    @SerialName("caption")
    val caption:String? = null,
    @SerialName("parse_mode")
    val parseMode:String? = null,
    @SerialName("caption_entities")
    val captionEntities:List<MessageEntity>? = null,
    @SerialName("reply_markup")
    val keyboard: InlineKeyboard? = null,
    @SerialName("input_message_content")
    val inputMessageContent:InputMessageContent? = null
): InlineQueryResult()
