package com.github.ehsannarmani.bot.model.update

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CallbackQuery(
    @SerialName("id")
    val id:String,
    @SerialName("from")
    val from: From,
    @SerialName("message")
    val message: Message? = null,
    @SerialName("chat_instance")
    val chatInstance:String,
    @SerialName("data")
    val data:String
)
