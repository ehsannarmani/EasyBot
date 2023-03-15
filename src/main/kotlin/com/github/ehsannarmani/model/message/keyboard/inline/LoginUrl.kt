package com.github.ehsannarmani.model.message.keyboard.inline

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginUrl(
    @SerialName("url")
    val url:String,
    @SerialName("forward_text")
    val forwardText:String,
    @SerialName("bot_username")
    val botUsername:String,
    @SerialName("request_write_access")
    val requestWriteAccess:Boolean
)
