package com.github.ehsannarmani

import com.github.ehsannarmani.model.message.*
import com.github.ehsannarmani.model.message.keyboard.ForceReply
import com.github.ehsannarmani.model.message.keyboard.InlineKeyboard
import com.github.ehsannarmani.model.message.keyboard.RemoveKeyboard
import com.github.ehsannarmani.model.message.keyboard.ReplyKeyboard
import com.github.ehsannarmani.model.message.keyboard.inline.InlineKeyboardItem
import com.github.ehsannarmani.model.message.keyboard.inline.LoginUrl
import com.github.ehsannarmani.model.message.keyboard.reply.ReplyKeyboardItem
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
                } else if (update.message?.text == "/group") {
                    bot.sendMediaGroup(
                        MediaGroupMessage(
                            chatId = update.message.chat.id.toString(),
                            media = listOf(
                                InputMediaPhoto(media = "https://cdn.britannica.com/45/5645-050-B9EC0205/head-treasure-flower-disk-flowers-inflorescence-ray.jpg"),
                                InputMediaAudio(media = "https://cdn.pixabay.com/download/audio/2022/05/27/audio_f8242dc80f.mp3?filename=edm-deep-house-ish-female-vocal-112184.mp3"),
                            )
                        )
                    )
                }
                if (update.callbackQuery != null){
                    if(update.callbackQuery.message.replyMarkup != null){
                        bot.sendMessage(TextMessage(chatId = update.callbackQuery.message.chat.id.toString(), text = "you are clicked"))
                    }
                }
            }

        },
        onErrorThrown = {
            println("\n" + it.message)
        }
    )

    bot.launch(
        webhookUrl = "https://fb71-94-131-98-78.eu.ngrok.io/bot"
    )

}