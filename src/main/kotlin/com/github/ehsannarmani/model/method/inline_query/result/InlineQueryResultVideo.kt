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
data class InlineQueryResultVideo(
    @SerialName("type")
    val type:String = "video",
    @SerialName("id")
    val id:String = UUID.randomUUID().toString(),
    @SerialName("video_url")
    val videoUrl:String,
    @SerialName("mime_type")
    val mimeType:String,
    @SerialName("thumbnail_url")
    val thumbnailUrl:String,
    @SerialName("title")
    val title:String,
    @SerialName("description")
    val description:String? = null,
    @SerialName("caption")
    val caption:String? = null,
    @SerialName("parse_mode")
    val parseMode:String? = null,
    @SerialName("caption_entities")
    val captionEntities:List<MessageEntity>? = null,
    @SerialName("video_width")
    val videoWidth:Int? = null,
    @SerialName("video_height")
    val videoHeight:Int? = null,
    @SerialName("video_duration")
    val videoDuration:Int? = null,
    @SerialName("reply_markup")
    val keyboard: InlineKeyboard? = null,
    @SerialName("input_message_content")
    val inputMessageContent:InputMessageContent? = null
): InlineQueryResult()
