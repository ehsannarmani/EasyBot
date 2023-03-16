package com.github.ehsannarmani.model.update

import com.github.ehsannarmani.model.message.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PollAnswer(
    @SerialName("poll_id")
    val pollId:String,
    @SerialName("user")
    val user: From,
    @SerialName("option_ids")
    val optionIds:List<Map<Int,Int>>
)
