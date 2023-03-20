package com.github.ehsannarmani.bot.model.method

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class StickerKeywords(
    @SerialName("sticker")
    val sticker:String,
    @SerialName("keywords")
    val keywords:List<String>
)
