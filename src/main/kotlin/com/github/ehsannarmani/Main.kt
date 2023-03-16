package com.github.ehsannarmani

import com.github.ehsannarmani.model.message.*
import com.github.ehsannarmani.model.method.ChangeChatPermissions
import com.github.ehsannarmani.model.method.ChatAdminCustomTitle
import com.github.ehsannarmani.model.method.ChatInviteLink
import com.github.ehsannarmani.model.method.ChatPermissions
import com.github.ehsannarmani.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

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
                } else if (update.message?.text?.contains("/sticker") == true) {
                    if(update.message.from.id.toInt() == 867396070){
                        val res = bot.setChatStickerSet(
                            chatId =update.message.chat.id.toString(),
                            stickerSetName = "@Cod_EXX"
                        )
                        bot.sendMessage(
                            TextMessage(
                                text = "set sticker set: ${res?.result}",
                                chatId = update.message.chat.id.toString(),
                                parseMode = "markdown"
                            )
                        )
                    }
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
        webhookUrl = "https://4d2a-94-131-98-78.eu.ngrok.io/bot",
        post = 3002
    )

}