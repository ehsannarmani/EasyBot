package com.github.ehsannarmani

import com.github.ehsannarmani.bot.Bot
import com.github.ehsannarmani.bot.model.message.keyboard.InlineKeyboard
import com.github.ehsannarmani.bot.model.message.keyboard.inline.InlineKeyboardItem
import com.github.ehsannarmani.bot.model.method.inline_query.AnswerInlineQuery
import com.github.ehsannarmani.bot.model.method.inline_query.result.InlineQueryResultArticle
import com.github.ehsannarmani.bot.model.method.inline_query.result.InlineQueryResultPhoto


fun main() {
    val bot = Bot(
        token = "5924861492:AAFYneEcpKeTrvchBAmR8zRM7LwYjOlRGe4",
        onUpdate = { update ->
            onMessage { msg ->
                onCommand("start"){
                    step("name")
                    reply("send your name")
                }
                onStep("name"){
                    step("age")
                    reply("send your age")
                }
                onStep("age"){
                    // do something
                }
            }

        },
        onErrorThrown = {
            println("err: ${it.message}")
        }
    )

    bot.startPolling()
//    bot.launch(
//        webhookUrl = "https://393b-94-131-98-78.eu.ngrok.io/bot",
//        post = 3002
//    )

}