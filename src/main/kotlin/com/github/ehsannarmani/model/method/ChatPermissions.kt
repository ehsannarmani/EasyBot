package com.github.ehsannarmani.model.method

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ChatPermissions(
    @SerialName("can_send_messages")
    val canSendMessages:Boolean? = null,
    @SerialName("can_send_audios")
    val canSendAudios:Boolean? = null,
    @SerialName("can_send_photos")
    val canSendPhotos:Boolean? = null,
    @SerialName("can_send_videos")
    val canSendVideos:Boolean? = null,
    @SerialName("can_send_video_notes")
    val canSendVideoNotes:Boolean? = null,
    @SerialName("can_send_voice_notes")
    val canSendVoiceNotes:Boolean? = null,
    @SerialName("can_send_polls")
    val canSendPolls:Boolean? = null,
    @SerialName("can_send_other_messages")
    val canSendOtherMessages:Boolean? = null,
    @SerialName("can_add_web_page_previews")
    val canAddWebPagePreviews:Boolean? = null,
    @SerialName("can_change_info")
    val canChangeInfo:Boolean? = null,
    @SerialName("can_invite_users")
    val canInviteUsers:Boolean? = null,
    @SerialName("can_pin_messages")
    val canPinMessages:Boolean? = null,
    @SerialName("can_manage_topics")
    val canManageTopics:Boolean? = null,
)
