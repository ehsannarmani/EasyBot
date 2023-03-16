package com.github.ehsannarmani.model.result

import com.github.ehsannarmani.model.method.ChatPermissions
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Chat(
    @SerialName("id")
    val id:Long,
    @SerialName("title")
    val title:String,
    @SerialName("type")
    val type:String,
    @SerialName("description")
    val description:String? = null,
    @SerialName("invite_link")
    val inviteLink:String? = null,
    @SerialName("permissions")
    val permissions:ChatPermissions? = null,
    @SerialName("join_to_send_messages")
    val joinToSendMessages:Boolean? = null,
    @SerialName("photo")
    val photo: Photo? = null
)

@Serializable
data class Photo(
    @SerialName("small_file_id")
    val smallFileId:String,
    @SerialName("small_file_unique_id")
    val smallFileUniqueId:String,
    @SerialName("big_file_id")
    val bigFileId:String,
    @SerialName("big_file_unique_id")
    val bigFileUniqueId:String,
)
