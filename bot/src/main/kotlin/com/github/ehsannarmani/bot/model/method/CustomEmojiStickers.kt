package com.github.ehsannarmani.bot.model.method

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CustomEmojiStickers(
    @SerialName("custom_emoji_ids")
    val customEmojiIds:List<String>
)
