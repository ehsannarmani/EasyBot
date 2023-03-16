package com.github.ehsannarmani.model.update

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    @SerialName("phone_number")
    val phoneNumber:String,
    @SerialName("first_name")
    val firstName:String,
    @SerialName("last_name")
    val lastName:String? = null,
    @SerialName("user_id")
    val userId:Long? = null
)
