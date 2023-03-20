package com.github.ehsannarmani.bot.model.message.keyboard

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WebAppInfo(
    @SerialName("url")
    val url:String
)
