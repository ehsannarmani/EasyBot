package com.github.ehsannarmani.bot.model.update

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Entity(
    @SerialName("offset")
    val offset:Int,
    @SerialName("length")
    val length:Int,
    @SerialName("type")
    val type:String
)
