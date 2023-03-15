package com.github.ehsannarmani.model.message.keyboard.reply

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestChat(
    @SerialName("request_id")
    val requestId:Int,
    @SerialName("chat_is_channel")
    val chatIsChannel:Boolean,
    @SerialName("chat_is_forum")
    val chatIsForum:Boolean,
    @SerialName("chat_has_username")
    val chatHasUsername:Boolean,
    @SerialName("chat_is_created")
    val chatIsCreated:Boolean,
    @SerialName("user_administrator_rights")
    val userAdministratorRights:ChatAdministratorRights,
    @SerialName("bot_administrator_rights")
    val botAdministratorRights: ChatAdministratorRights,
    @SerialName("bot_is_member")
    val botIsMember:Boolean
)
