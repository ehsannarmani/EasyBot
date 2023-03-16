package com.github.ehsannarmani.model.method

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserProfilePhotos(
    @SerialName("user_id")
    val userId:Long,
    @SerialName("offset")
    val offset:Int? = null,
    @SerialName("limit")
    val limit:Int? = null
)
