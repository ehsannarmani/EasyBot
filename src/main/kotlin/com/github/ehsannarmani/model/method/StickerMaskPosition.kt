package com.github.ehsannarmani.model.method

import com.github.ehsannarmani.model.result.MaskPosition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class StickerMaskPosition(
    @SerialName("sticker")
    val sticker:String,
    @SerialName("mask_position")
    val maskPosition:MaskPosition
)
