package com.github.ehsannarmani.model.method

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ForumTopic(
    @SerialName("chat_id")
    val chatId:String,
    @SerialName("name")
    val name:String,
    @SerialName("icon_color")
    val iconColor:Int? = null,
    @SerialName("icon_custom_emoji_id")
    val iconCustomEmojiId:String? = null
)
