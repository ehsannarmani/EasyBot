package com.github.ehsannarmani.bot.model.method.inline_query.result

import com.github.ehsannarmani.bot.model.message.MessageEntity
import com.github.ehsannarmani.bot.model.message.keyboard.InlineKeyboard
import com.github.ehsannarmani.bot.model.message.keyboard.Keyboard
import com.github.ehsannarmani.bot.model.method.inline_query.InputMessageContent
import com.github.ehsannarmani.bot.model.method.inline_query.result.InlineQueryResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID


@Serializable
data class InlineQueryResultCachedDocument(
    @SerialName("type")
    val type:String = "document",
    @SerialName("id")
    val id:String = UUID.randomUUID().toString(),
    @SerialName("title")
    val title:String,
    @SerialName("document_file_id")
    val documentFileId:String,
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
