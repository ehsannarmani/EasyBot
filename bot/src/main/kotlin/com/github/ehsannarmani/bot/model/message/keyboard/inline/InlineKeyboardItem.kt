package com.github.ehsannarmani.bot.model.message.keyboard.inline

import com.github.ehsannarmani.bot.model.message.keyboard.WebAppInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InlineKeyboardItem(
    @SerialName("text")
    val text:String,
    @SerialName("callback_data")
    val callbackData:String?  = null,
    @SerialName("url")
    val url:String? = null,
    @SerialName("web_app")
    val webApp:WebAppInfo? = null,
    @SerialName("login_url")
    val loginUrl:LoginUrl? = null,
    @SerialName("switch_inline_query")
    val switchInlineQuery:String? = null,
    @SerialName("switch_inline_query_current_chat")
    val switchInlineQueryCurrentChat:String? = null,
    @SerialName("pay")
    val pay:Boolean? = null,
)