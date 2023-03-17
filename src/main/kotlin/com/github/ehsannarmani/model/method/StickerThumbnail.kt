package com.github.ehsannarmani.model.method

import com.github.ehsannarmani.model.result.MaskPosition
import com.github.ehsannarmani.model.update.Thumbnail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class StickerThumbnail(
    @SerialName("name")
    val name:String,
    @SerialName("user_id")
    val userId:Long,
    @SerialName("thumbnail")
    val thumbnail:Thumbnail
)
