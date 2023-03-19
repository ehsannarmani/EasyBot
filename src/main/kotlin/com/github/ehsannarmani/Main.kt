package com.github.ehsannarmani

import com.fasterxml.jackson.core.type.TypeReference
import com.github.ehsannarmani.model.message.*
import com.github.ehsannarmani.model.message.keyboard.InlineKeyboard
import com.github.ehsannarmani.model.message.keyboard.ReplyKeyboard
import com.github.ehsannarmani.model.message.keyboard.inline.InlineKeyboardItem
import com.github.ehsannarmani.model.message.keyboard.reply.ReplyKeyboardItem
import com.github.ehsannarmani.model.method.*
import com.github.ehsannarmani.model.method.command.Commands
import com.github.ehsannarmani.model.method.inline_query.AnswerInlineQuery
import com.github.ehsannarmani.model.method.inline_query.InputMessageContent
import com.github.ehsannarmani.model.method.inline_query.result.InlineQueryResultArticle
import com.github.ehsannarmani.model.method.inline_query.result.InlineQueryResultContact
import com.github.ehsannarmani.model.updating_messages.EditTextMessage
import com.github.ehsannarmani.model.updating_messages.MessageReplyMarkup
import com.github.ehsannarmani.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.koin.ext.getFullName
import java.lang.reflect.ParameterizedType
import kotlin.reflect.full.starProjectedType


@Serializable
data class Media<T>(
    @SerialName("name") val name: String,
    @SerialName("media") val media: T,
)

@Serializable
data class Grade(@SerialName("grade") val grade:String)





fun main() {
    val bot = Bot(
        token = Constants.TOKEN,
        onUpdate = { update ->
            onMessage { msg ->
                onText("db") {

                    msg.from.putData(
                        key = "media2",
                        data = Media(
                            name = "media2",
                            media = Grade("grade name")
                        )
                    )
                }
                onText("get") {
                    println("\n\n${
                        msg.from.getData<Media<Grade>>()
                    }")
                }

            }

        }
    )

    bot.launch(
        webhookUrl = "https://393b-94-131-98-78.eu.ngrok.io/bot",
        post = 3002
    )

}