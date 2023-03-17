package com.github.ehsannarmani.model.update

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class InlineQuery(
    @SerialName("id")
    val id:String,
    @SerialName("from")
    val from: From,
    @SerialName("chat_type")
    val chatType:String,
    @SerialName("query")
    val query:String,
    @SerialName("offset")
    val offset:String
)
