package com.github.ehsannarmani.bot.model.method

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class StickerToSet(
    @SerialName("user_id")
    val userId:Long,
    @SerialName("name")
    val name:String,
    @SerialName("sticker")
    val inputSticker: InputSticker
)
