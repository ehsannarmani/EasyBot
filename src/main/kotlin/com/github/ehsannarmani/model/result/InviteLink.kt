package com.github.ehsannarmani.model.result

import com.github.ehsannarmani.model.update.From
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class InviteLink(
    @SerialName("invite_link")
    val inviteLink: String,
    @SerialName("name")
    val name: String? = null,
    @SerialName("creator")
    val creator: From? = null,
    @SerialName("creates_join_request")
    val createsJoinRequest: Boolean? = null,
    @SerialName("is_primary")
    val isPrimary: Boolean? = null,
    @SerialName("is_revoked")
    val isRevoked: Boolean? = null,
    @SerialName("expire_date")
    val expireDate: Int? = null,
    @SerialName("member_limit")
    val memberLimit: Int? = null,
    @SerialName("pending_join_request_count")
    val pendingJoinRequestCount: Int? = null,
)