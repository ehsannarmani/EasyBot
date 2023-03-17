package com.github.ehsannarmani.model.method

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GameScore(
    @SerialName("user_id")
    val userId:Long,
    @SerialName("score")
    val score:Int,
    @SerialName("force")
    val force:Boolean? = null,
    @SerialName("disable_edit_message")
    val disableEditMessage:Boolean?  =null,
    @SerialName("chat_id")
    val chatId:String? = null,
    @SerialName("message_id")
    val messageId:Int? = null,
    @SerialName("inline_message_id")
    val inlineMessageId:String? = null,

    )
