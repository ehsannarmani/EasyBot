package com.github.ehsannarmani.model.method.inline_query

import com.github.ehsannarmani.model.message.MessageEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class InputMessageContent(
    @SerialName("message_text")
    val messageText:String,
    @SerialName("parse_mode")
    val parseMode:String? = null,
    @SerialName("entities")
    val entities:List<MessageEntity>? = null,
    @SerialName("disable_web_page_preview")
    val disableWebpagePreview:Boolean? = null
)
