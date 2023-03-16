package com.github.ehsannarmani.model.method

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class File(
    @SerialName("file_id")
    val fileId:String,
    @SerialName("file_unique_id")
    val fileUniqueId:String,
    @SerialName("file_path")
    val filePath:String? = null,
    @SerialName("file_size")
    val fileSize:Long,
    @SerialName("width")
    val width:Int,
    @SerialName("height")
    val height:Int,
)
