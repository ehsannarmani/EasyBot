package com.github.ehsannarmani.model.update

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Update(
    @SerialName("update_id")
    val updateId:Long,
    @SerialName("message")
    val message: Message? = null,
    @SerialName("callback_query")
    val callbackQuery: CallbackQuery? = null,
    @SerialName("my_chat_member")
    val myChatMember: MyChatMember? = null,
    @SerialName("poll_answer")
    val pollAnswer: PollAnswer? = null,
)