package com.github.ehsannarmani.bot.model.message.keyboard.reply

import com.github.ehsannarmani.bot.model.message.keyboard.WebAppInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable()
data class ReplyKeyboardItem(
    @SerialName("text")
    val text:String,
    @SerialName("request_contact")
    val requestContact:Boolean = false,
    @SerialName("request_location")
    val requestLocation:Boolean = false,
    @SerialName("request_user")
    val requestUser: RequestUser? = null,
    @SerialName("request_chat")
    val requestChat: RequestChat? = null,
    @SerialName("request_poll")
    val requestPoll: RequestPoll? = null,
    @SerialName("web_app")
    val webApp: WebAppInfo? = null,
)