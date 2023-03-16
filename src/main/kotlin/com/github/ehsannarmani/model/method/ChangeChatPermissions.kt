package com.github.ehsannarmani.model.method

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ChangeChatPermissions(
    @SerialName("chat_id")
    val chatId:String,
    @SerialName("permissions")
    val permissions: ChatPermissions,
    @SerialName("use_independent_chat_permissions")
    val useIndependentChatPermissions:Boolean? = null
)
