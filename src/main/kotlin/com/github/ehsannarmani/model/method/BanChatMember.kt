package com.github.ehsannarmani.model.method

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BanChatMember(
    @SerialName("chat_id")
    val chatId:String,
    @SerialName("user_id")
    val userId:Long,
    @SerialName("until_date")
    val untilDate:Int? = null,
    @SerialName("revoke_messages")
    val revokeMessages:Boolean? = null
)
