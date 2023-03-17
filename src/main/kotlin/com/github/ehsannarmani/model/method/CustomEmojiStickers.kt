package com.github.ehsannarmani.model.method

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CustomEmojiStickers(
    @SerialName("custom_emoji_ids")
    val customEmojiIds:List<String>
)
