package com.github.ehsannarmani.model.method.inline_query.result

import com.github.ehsannarmani.model.message.keyboard.InlineKeyboard
import com.github.ehsannarmani.model.method.inline_query.InputMessageContent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID


@Serializable
data class InlineQueryResultGame(
    @SerialName("type")
    val type:String = "game",
    @SerialName("id")
    val id:String = UUID.randomUUID().toString(),
    @SerialName("game_short_name")
    val gameShortName:String,
    @SerialName("reply_markup")
    val keyboard: InlineKeyboard? = null,
): InlineQueryResult()
