package com.github.ehsannarmani.model.update

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sticker(
    @SerialName("width")
    val width:Int,
    @SerialName("height")
    val height:Int,
    @SerialName("emoji")
    val emoji:String? = null,
    @SerialName("set_name")
    val stickerSetName:String? = null,
    @SerialName("is_animated")
    val isAnimated:Boolean,
    @SerialName("is_video")
    val isVideo:Boolean,
    @SerialName("type")
    val type:String,
    @SerialName("thumbnail")
    val thumbnail: Thumbnail,
    @SerialName("thumb")
    val thumb: Thumbnail,
    @SerialName("file_id")
    val fileId: String,
    @SerialName("file_unique_id")
    val fileUniqueId: String,
    @SerialName("file_size")
    val fileSize: Long,
)
