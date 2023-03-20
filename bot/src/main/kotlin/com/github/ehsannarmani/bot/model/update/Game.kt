package com.github.ehsannarmani.bot.model.update

import com.github.ehsannarmani.bot.model.method.File
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
