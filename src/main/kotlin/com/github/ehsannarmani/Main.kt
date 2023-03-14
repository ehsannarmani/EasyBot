package com.github.ehsannarmani

import com.github.ehsannarmani.model.message.TextMessage
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
                if(update.message?.text == "/test"){
                    bot.sendMessage(TextMessage(
                        text = "this is test",
                        chatId = update.message.chat.id
                    ))
                }
            }

        },
        onErrorThrown = {
            println("\n" + it.message)
        }
    )

    bot.launch(
        webhookUrl = "https://b018-94-131-98-78.eu.ngrok.io/bot"
    )

}