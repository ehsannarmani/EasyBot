package com.github.ehsannarmani.bot.model.method

import com.github.ehsannarmani.bot.model.result.MaskPosition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class StickerMaskPosition(
    @SerialName("sticker")
    val sticker:String,
    @SerialName("mask_position")
    val maskPosition:MaskPosition
)
