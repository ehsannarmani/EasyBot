package com.github.ehsannarmani.bot.model.update

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Document(
    @SerialName("file_name")
    val fileName:String? = null,
    @SerialName("mime_type")
    val mimeType:String,
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
