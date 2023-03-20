package com.github.ehsannarmani.bot.model.method

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RestrictChatMember(
    @SerialName("chat_id")
    val chatId:String,
    @SerialName("user_id")
    val userId:Long,
    @SerialName("permissions")
    val permissions:ChatPermissions,
    @SerialName("use_independent_chat_permissions")
    val useIndependentChatPermissions:Boolean? = null,
    @SerialName("until_date")
    val untilDate:Int? = null
)
