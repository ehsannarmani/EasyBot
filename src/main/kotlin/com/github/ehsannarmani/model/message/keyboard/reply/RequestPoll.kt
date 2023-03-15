package com.github.ehsannarmani.model.message.keyboard.reply

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RequestPoll(
    @SerialName("type")
    val type:String
)
