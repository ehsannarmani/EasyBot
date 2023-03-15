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
                } else if (update.message?.text == "/doc") {
                    bot.sendDocument(
                        DocumentMessage(
                            chatId = update.message.chat.id.toString(),
                            document = "http://chap.sch.ir/sites/default/files/lbooks/97-98/30/001-016%20C111214.pdf",
                            caption = "hello",
                            keyboard = InlineKeyboard(
                                keyboard = listOf(
                                    listOf(
                                        InlineKeyboardItem(text = "hello", callbackData = "test"),
                                        InlineKeyboardItem(text = "hello", callbackData = "test"),
                                    ),
                                    listOf(
                                        InlineKeyboardItem(text = "hello", callbackData = "test")
                                    ),
                                )
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
        webhookUrl = "https://d78a-94-131-98-78.eu.ngrok.io/bot"
    )

}