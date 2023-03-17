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
data class InlineQueryResultCachedSticker(
    @SerialName("type")
    val type:String = "sticker",
    @SerialName("id")
    val id:String = UUID.randomUUID().toString(),
    @SerialName("sticker_file_id")
    val stickerFileId:String,
    @SerialName("reply_markup")
    val keyboard: InlineKeyboard? = null,
    @SerialName("input_message_content")
    val inputMessageContent:InputMessageContent? = null
): InlineQueryResult()
