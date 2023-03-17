package com.github.ehsannarmani.model.method.command.scope

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class BotCommandScope


@Serializable()
@SerialName("default")
data class BotCommandScopeDefault(
    @SerialName("type")
    val type:String = "default"
): BotCommandScope()

@Serializable()
@SerialName("all_private_chats")
data class BotCommandScopeAllPrivateChats(
    @SerialName("type")
    val type:String = "all_private_chats"
): BotCommandScope()

@Serializable()
@SerialName("all_group_chats")
data class BotCommandScopeAllGroupChats(
    @SerialName("type")
    val type:String = "all_group_chats"
): BotCommandScope()

@Serializable()
@SerialName("all_chat_administrators")
data class BotCommandScopeAllChatAdministrators(
    @SerialName("type")
    val type:String = "all_chat_administrators"
): BotCommandScope()

@Serializable()
@SerialName("chat")
data class BotCommandScopeChat(
    @SerialName("type")
    val type:String = "chat",
    @SerialName("chat_id")
    val chatId:String
): BotCommandScope()


@Serializable()
@SerialName("chat_administrators")
data class BotCommandScopeChatAdministrators(
    @SerialName("type")
    val type:String = "chat_administrators",
    @SerialName("chat_id")
    val chatId:String
): BotCommandScope()

@Serializable()
@SerialName("chat_member")
data class BotCommandScopeChatMember(
    @SerialName("type")
    val type:String = "chat_member",
    @SerialName("chat_id")
    val chatId:String,
    @SerialName("user_id")
    val userId:Long
): BotCommandScope()