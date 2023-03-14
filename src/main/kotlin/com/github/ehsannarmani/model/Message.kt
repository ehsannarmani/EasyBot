package com.github.ehsannarmani.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Message(
    @SerialName("message_id")
    val messageId:Int,
    @SerialName("from")
    val from: From,
    @SerialName("chat")
    val chat: Chat,
    @SerialName("date")
    val date:Long,
    @SerialName("text")
    val text:String? = null,
    @SerialName("sticker")
    val sticker:Sticker? = null,
    @SerialName("animation")
    val animation:Animation? = null,
    @SerialName("document")
    val document:Document? = null,
    @SerialName("entities")
    val entities:List<Entity>? = null
)