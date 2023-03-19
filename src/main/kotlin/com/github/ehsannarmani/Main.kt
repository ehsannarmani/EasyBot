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


fun main() {
    val bot = Bot(
        token = Constants.TOKEN,
        onUpdate = { update ->
            onMessage { msg ->
                onText("/start"){
                    update.message?.from?.reRegister()
                    println("registered")
                }

                onText("users"){
                    println("\nusers: ${getUsers()}")
                }
                onText("polling"){
                    reply("polling working successfully")
                }

            }

        }
    )

    bot.startPolling()
//    bot.launch(
//        webhookUrl = "https://393b-94-131-98-78.eu.ngrok.io/bot",
//        post = 3002
//    )

}