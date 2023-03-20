package com.github.ehsannarmani.bot.model.method

import com.github.ehsannarmani.bot.model.result.MaskPosition
import com.github.ehsannarmani.bot.model.result.StickerType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class NewStickerSet(
    @SerialName("user_id")
    val userId:Long,
    @SerialName("name")
    val name:String,
    @SerialName("title")
    val title:String,
    @SerialName("stickers")
    val stickers:List<InputSticker>
)

@Serializable
data class InputSticker(
    @SerialName("sticker")
    val sticker:String,
    @SerialName("emoji_list")
    val emojiList:List<String>,
    @SerialName("mask_position")
    val maskPosition: MaskPosition,
    @SerialName("keywords")
    val keywords:List<String>,
    @SerialName("sticker_format")
    val stickerFormat: StickerFormat,
    @SerialName("sticker_type")
    val stickerType: StickerType? = null,
    @SerialName("needs_repainting")
    val needsRepainting:Boolean? = null
)


@Serializable
enum class StickerFormat{
    @SerialName("static") Static,
    @SerialName("animated") Animated,
    @SerialName("video") Video,
}
