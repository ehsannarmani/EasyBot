package com.github.ehsannarmani.model.update

import com.github.ehsannarmani.model.method.File
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Game(
    @SerialName("title")
    val title:String,
    @SerialName("description")
    val description:String,
    @SerialName("photo")
    val photo:List<File>,
)
