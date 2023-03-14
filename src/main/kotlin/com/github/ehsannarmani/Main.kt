package com.github.ehsannarmani

import com.github.ehsannarmani.utils.Constants

fun main() {
    val bot = Bot(
        token = Constants.TOKEN,
        onUpdate = {
            println("\n")
            if(it.message.sticker != null){
                println("\n"+it.message.sticker.fileId)
            }else if(it.message.animation != null){
                println("\n"+"animation: ${it.message.animation.duration}")
            }
        }
    )

    bot.launch(
        webhookUrl = "https://3630-94-131-98-78.eu.ngrok.io/bot"
    )
}