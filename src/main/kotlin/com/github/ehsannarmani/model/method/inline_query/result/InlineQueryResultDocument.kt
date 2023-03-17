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
data class InlineQueryResultDocument(
    @SerialName("type")
    val type:String = "document",
    @SerialName("id")
    val id:String = UUID.randomUUID().toString(),
    @SerialName("title")
    val title:String,
    @SerialName("document_url")
    val documentUrl:String,
    @SerialName("mime_type")
    val mimeType:String,
    @SerialName("description")
    val description:String? = null,
    @SerialName("caption")
    val caption:String? = null,
    @SerialName("parse_mode")
    val parseMode:String? = null,
    @SerialName("caption_entities")
    val captionEntities:List<MessageEntity>? = null,
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
