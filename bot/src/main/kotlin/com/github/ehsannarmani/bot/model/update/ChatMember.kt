package com.github.ehsannarmani.bot.model.update

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatMember(
    @SerialName("user")
    val user: From,
    @SerialName("status")
    val status:String,
    @SerialName("can_be_edited")
    val canBeEdited:Boolean? = null,
    @SerialName("can_change_info")
    val canChangeInfo:Boolean? = null,
    @SerialName("can_delete_messages")
    val canDeleteMessages:Boolean? = null,
    @SerialName("can_invite_users")
    val canInviteUsers:Boolean? = null,
    @SerialName("can_manage_chat")
    val canManageChat:Boolean? = null,
    @SerialName("can_manage_topics")
    val canManageTopics:Boolean? = null,
    @SerialName("can_manage_video_chats")
    val canManageVideoChats:Boolean? = null,
    @SerialName("can_manage_voice_chats")
    val canManageVoiceChats:Boolean? = null,
    @SerialName("can_pin_messages")
    val canPinMessages:Boolean? = null,
    @SerialName("can_promote_members")
    val canPromoteMembers:Boolean? = null,
    @SerialName("can_restrict_members")
    val canRestrictMembers:Boolean? = null,
    @SerialName("is_anonymous")
    val isAnonymous:Boolean? = null,
)
