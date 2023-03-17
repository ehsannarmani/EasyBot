package com.github.ehsannarmani.model.method

import com.github.ehsannarmani.model.message.keyboard.reply.ChatAdministratorRights
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DefaultAdministratorRights(
    @SerialName("rights")
    val rights: ChatAdministratorRights? = null,
    @SerialName("for_channels")
    val forChannels:Boolean? = null
)
