package com.github.ehsannarmani.model.update

import com.github.ehsannarmani.model.message.User
import com.github.ehsannarmani.model.result.Chat
import com.github.ehsannarmani.model.result.InviteLink
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ChatJoinRequest(
    @SerialName("chat") val chat:Chat,
    @SerialName("from") val from: User,
    @SerialName("user_chat_id") val userChatId:Long,
    @SerialName("date") val date:Long,
    @SerialName("bio") val bio:String? = null,
    @SerialName("invite_link") val inviteLink: InviteLink
)


