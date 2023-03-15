package com.github.ehsannarmani.model.update

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class VideoNote(
    @SerialName("duration")
    val duration:Long,
    @SerialName("length")
    val length:Int,
    @SerialName("thumbnail")
    val thumbnail: Thumbnail? = null,
    @SerialName("thumb")
    val thumb: Thumbnail? = null,
    @SerialName("file_id")
    val fileId:String,
    @SerialName("file_unique_id")
    val fileUniqueId:String,
    @SerialName("file_size")
    val fileSize:Long,
)
