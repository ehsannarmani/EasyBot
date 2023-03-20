package com.github.ehsannarmani.bot.model.message.keyboard.reply

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestUser(
    @SerialName("request_id")
    val requestId:Int,
    @SerialName("user_is_bot")
    val userIsBot:Boolean,
    @SerialName("user_is_premium")
    val userIsPremium:Boolean
)
