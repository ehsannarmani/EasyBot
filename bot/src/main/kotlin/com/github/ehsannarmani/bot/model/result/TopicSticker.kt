package com.github.ehsannarmani.bot.model.result

import com.github.ehsannarmani.bot.model.method.File
import com.github.ehsannarmani.bot.model.update.Thumbnail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TopicSticker(
    @SerialName("file_id")
    val fileId:String,
    @SerialName("file_unique_id")
    val fileUniqueId:String,
    @SerialName("type")
    val type:String,
    @SerialName("width")
    val width:Int,
    @SerialName("height")
    val height:Int,
    @SerialName("is_animated")
    val isAnimated:Boolean,
    @SerialName("is_video")
    val isVideo:Boolean,
    @SerialName("thumbnail")
    val thumbnail: Thumbnail? = null,
    @SerialName("emoji")
    val emoji:String,
    @SerialName("set_name")
    val setName:String,
    @SerialName("custom_emoji_id")
    val customEmojiId:String? = null,
    @SerialName("needs_repainting")
    val needsRepainting:Boolean? = null,
    @SerialName("mask_position")
    val maskPosition:MaskPosition? = null,
    @SerialName("premium_animation")
    val premiumAnimation:File? = null
)

@Serializable
data class MaskPosition(
    @SerialName("point")
    val point:String,
    @SerialName("x_shift")
    val xShift:Float,
    @SerialName("y_shift")
    val yShift:Float,
    @SerialName("scale")
    val scale:Float
)
