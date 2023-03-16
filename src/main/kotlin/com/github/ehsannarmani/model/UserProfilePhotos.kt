package com.github.ehsannarmani.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserProfilePhotos(
    @SerialName("user_id")
    val userId:Int,
    @SerialName("offset")
    val offset:Int? = null,
    @SerialName("limit")
    val limit:Int? = null
)
