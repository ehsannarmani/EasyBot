package com.github.ehsannarmani.model.method.inline_query.result

import com.github.ehsannarmani.model.message.MessageEntity
import com.github.ehsannarmani.model.message.keyboard.InlineKeyboard
import com.github.ehsannarmani.model.method.inline_query.InputMessageContent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID


@Serializable
data class InlineQueryResultArticle(
    @SerialName("type")
    val type:String = "article",
    @SerialName("id")
    val id:String = UUID.randomUUID().toString(),
    @SerialName("title")
    val title:String,
    @SerialName("url")
    val url:String? = null,
    @SerialName("hide_url")
    val hideUrl:String? = null,
    @SerialName("description")
    val description:String? = null,
    @SerialName("thumbnail_url")
    val thumbnailUrl:String? = null,
    @SerialName("thumbnail_width")
    val thumbnailWidth:Int? = null,
    @SerialName("thumbnail_height")
    val thumbnailHeight:Int? = null,
    @SerialName("caption_entities")
    val captionEntities:List<MessageEntity>? = null,
    @SerialName("reply_markup")
    val keyboard: InlineKeyboard? = null,
    @SerialName("input_message_content")
    val inputMessageContent:InputMessageContent? = null
): InlineQueryResult()
