package com.github.ehsannarmani.bot.model.update

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Photo(
    @SerialName("file_id")
    val fileId:String,
    @SerialName("file_unique_id")
    val fileUniqueId:String,
    @SerialName("file_size")
    val fileSize:Long,
    @SerialName("width")
    val width:Int,
    @SerialName("height")
    val height:Int,
)
