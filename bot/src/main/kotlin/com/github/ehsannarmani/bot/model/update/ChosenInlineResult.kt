package com.github.ehsannarmani.bot.model.update

import com.github.ehsannarmani.bot.model.message.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ChosenInlineResult(
    @SerialName("result_id")
    val resultId:String,
    @SerialName("from")
    val from: User,
    @SerialName("location")
    val location: Location? = null,
    @SerialName("inline_message_id")
    val inlineMessageId:String? = null,
    @SerialName("query")
    val query:String
)
