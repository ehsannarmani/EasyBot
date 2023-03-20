package com.github.ehsannarmani.bot.model.result

import com.github.ehsannarmani.bot.model.update.Sticker
import com.github.ehsannarmani.bot.model.update.Thumbnail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class StickerSet(
    @SerialName("name")
    val name:String,
    @SerialName("title")
    val title:String,
    @SerialName("sticker_type")
    val stickerType:StickerType,
    @SerialName("is_animated")
    val isAnimated:Boolean,
    @SerialName("is_video")
    val isVideo:Boolean,
    @SerialName("stickers")
    val stickers:List<TopicSticker>,
    @SerialName("thumbnail")
    val thumbnail:Thumbnail? = null,
)

@Serializable
enum class StickerType{
    @SerialName("regular") Regular,
    @SerialName("mask") Mask,
    @SerialName("custom_emoji") CustomEmoji,
}
