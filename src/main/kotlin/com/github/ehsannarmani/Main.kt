package com.github.ehsannarmani

import com.github.ehsannarmani.model.message.*
import com.github.ehsannarmani.model.method.*
import com.github.ehsannarmani.model.method.command.Commands
import com.github.ehsannarmani.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun main() {
    val scope = CoroutineScope(Dispatchers.IO)
    val bot = Bot(
        token = Constants.TOKEN,
        onUpdate = { update, bot ->
            scope.launch {
                if (update.message?.text == "/test") {
                    print("\n\n\n${bot.getMe()}")
                    bot.sendMessage(
                        TextMessage(
                            text = "Im ${bot.getMe()?.result?.firstName}",
                            chatId = update.message.chat.id.toString(),
                            parseMode = "html"
                        )
                    )
                } else if (update.message?.text?.contains("/set") == true) {
                    if (update.message.from.id.toInt() == 867396070) {
                        val res = bot.setMyShortDescription(
                            shortDescription = update.message.text.replace("/set ","")
                        )
                        bot.sendMessage(
                            TextMessage(
                                text = "desc set: ${res?.result}" ,
                                chatId = update.message.chat.id.toString(),
                                parseMode = "html"
                            )
                        )
                    }
                }else if (update.message?.text?.contains("/get") == true) {
                    if (update.message.from.id.toInt() == 867396070) {
                        val res = bot.getMyShortDescription()
                        bot.sendMessage(
                            TextMessage(
                                text = "desc get: ${res?.result?.shortDescription}" ,
                                chatId = update.message.chat.id.toString(),
                                parseMode = "html"
                            )
                        )
                    }
                }
                if (update.callbackQuery != null) {
                    bot.answerCallbackQuery(
                        CallbackQuery(
                            id = update.callbackQuery.id,
                            text = "This is callback query!",
                            url = "https://google.com",
                            showAlert = true
                        )
                    )
                }
            }

        },
        onErrorThrown = {
            println("\n" + it.message)
        }
    )

    bot.launch(
        webhookUrl = "https://65e5-94-131-98-78.eu.ngrok.io/bot",
        post = 3002
    )

}