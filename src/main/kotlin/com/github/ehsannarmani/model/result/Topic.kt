package com.github.ehsannarmani.model.result

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Topic(
    @SerialName("message_thread_id")
    val messageThreadId:Int? = null,
    @SerialName("name")
    val name:String,
    @SerialName("icon_color")
    val iconColor:Int? = null,
    @SerialName("icon_custom_emoji_id")
    val iconCustomEmojiId:String? = null
)
