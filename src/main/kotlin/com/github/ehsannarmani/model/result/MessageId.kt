package com.github.ehsannarmani.model.result

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageId(
    @SerialName("message_id")
    val messageId:Int
)
