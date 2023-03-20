package com.github.ehsannarmani.bot.model.update

import com.github.ehsannarmani.bot.model.result.Chat
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MyChatMember(
    @SerialName("chat")
    val chat: Chat,
    @SerialName("from")
    val from: From,
    @SerialName("date")
    val date:Long,
    @SerialName("old_chat_member")
    val oldChatMember: ChatMember,
    @SerialName("new_chat_member")
    val newChatMember: ChatMember,
)
