package com.github.ehsannarmani

import com.github.ehsannarmani.utils.Constants

fun main() {
    val bot = Bot(
        token = Constants.TOKEN,
        onUpdate = {
            print("\n"+it.message?.messageType)
        },
        onErrorThrown = {
            println("\n"+it.message)
        }
    )

    bot.launch(
        webhookUrl = "https://1864-94-131-98-78.eu.ngrok.io/bot"
    )
}