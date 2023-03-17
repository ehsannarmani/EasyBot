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
data class InlineQueryResultAudio(
    @SerialName("type")
    val type:String = "audio",
    @SerialName("id")
    val id:String = UUID.randomUUID().toString(),
    @SerialName("audio_url")
    val audioUrl:String,
    @SerialName("title")
    val title:String,
    @SerialName("caption")
    val caption:String? = null,
    @SerialName("parse_mode")
    val parseMode:String? = null,
    @SerialName("caption_entities")
    val captionEntities:List<MessageEntity>? = null,
    @SerialName("performer")
    val performer:String? = null,
    @SerialName("audio_duration")
    val audioDuration:Int? = null,
    @SerialName("reply_markup")
    val keyboard: InlineKeyboard? = null,
    @SerialName("input_message_content")
    val inputMessageContent:InputMessageContent? = null
): InlineQueryResult()
