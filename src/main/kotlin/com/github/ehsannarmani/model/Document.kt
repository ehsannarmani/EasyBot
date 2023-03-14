package com.github.ehsannarmani.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Document(
    @SerialName("file_name")
    val fileName:String,
    @SerialName("mime_type")
    val mimeType:String,
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
