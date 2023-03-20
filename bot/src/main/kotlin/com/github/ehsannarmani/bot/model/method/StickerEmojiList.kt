package com.github.ehsannarmani.bot.model.method

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class StickerEmojiList(
    @SerialName("sticker")
    val sticker:String,
    @SerialName("emoji_list")
    val emojiList:List<String>
)
