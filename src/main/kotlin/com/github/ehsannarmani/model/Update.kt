package com.github.ehsannarmani.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Update(
    @SerialName("update_id")
    val updateId:Long,
    @SerialName("message")
    val message: Message,
)