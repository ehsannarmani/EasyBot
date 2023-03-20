package com.github.ehsannarmani

import com.github.ehsannarmani.bot.Bot
import com.github.ehsannarmani.bot.model.message.keyboard.InlineKeyboard
import com.github.ehsannarmani.bot.model.message.keyboard.inline.InlineKeyboardItem
import com.github.ehsannarmani.bot.model.method.inline_query.AnswerInlineQuery
import com.github.ehsannarmani.bot.model.method.inline_query.result.InlineQueryResultArticle
import com.github.ehsannarmani.bot.model.method.inline_query.result.InlineQueryResultPhoto
import com.github.ehsannarmani.bot.model.update.MessageType
import com.github.ehsannarmani.bot.model.update.UpdateType


fun main() {
    val bot = Bot(
        token = "5924861492:AAFYneEcpKeTrvchBAmR8zRM7LwYjOlRGe4",
        onUpdate = { update ->
            onMessage { msg ->
                when(msg.messageType){
                    MessageType.Text -> print("Text Message Received")
                    MessageType.Photo -> print("Photo Message Received")
                    MessageType.Audio -> print("Audio Message Received")
                    MessageType.Voice -> print("Voice Message Received")
                    MessageType.Document -> print("Document Message Received")
                    MessageType.Gif -> print("Gif Message Received")
                    MessageType.Sticker -> print("Sticker Message Received")
                    MessageType.Poll -> print("Poll Message Received")
                    MessageType.Game -> print("Game Message Received")
                    MessageType.Invoice -> print("Invoice Message Received")
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