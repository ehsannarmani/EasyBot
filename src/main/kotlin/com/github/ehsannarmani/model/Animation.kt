package com.github.ehsannarmani.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Animation(
    @SerialName("file_name")
    val fileName:String,
    @SerialName("mime_type")
    val mimeType:String,
    @SerialName("duration")
    val duration:Long,
    @SerialName("width")
    val width:Int,
    @SerialName("height")
    val height:Int,
    @SerialName("thumbnail")
    val thumbnail:Thumbnail,
    @SerialName("thumb")
    val thumb:Thumbnail,
    @SerialName("file_id")
    val fileId:String,
    @SerialName("file_unique_id")
    val fileUniqueId:String,
    @SerialName("file_size")
    val fileSize:Long,
)
