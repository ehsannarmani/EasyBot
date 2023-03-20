package com.github.ehsannarmani

import com.github.ehsannarmani.bot.Bot
import com.github.ehsannarmani.bot.model.message.keyboard.InlineKeyboard
import com.github.ehsannarmani.bot.model.message.keyboard.inline.InlineKeyboardItem


fun main() {
    val bot = Bot(
        token = "5924861492:AAFYneEcpKeTrvchBAmR8zRM7LwYjOlRGe4",
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
                    reply("polling working successfully", keyboard = InlineKeyboard(
                        keyboard = listOf(
                            listOf(
                                InlineKeyboardItem(text = "Key 1", callbackData = "key1").onCLick {
                                    it.answer("key1 pressed")
                                },
                                InlineKeyboardItem(text = "Key 2", callbackData = "key2").onCLick {
                                    it.answer("key2 pressed")
                                },
                            )
                        )
                    )
                    )
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