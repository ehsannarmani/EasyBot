package com.github.ehsannarmani

import com.github.ehsannarmani.model.message.*
import com.github.ehsannarmani.model.message.keyboard.InlineKeyboard
import com.github.ehsannarmani.model.message.keyboard.inline.InlineKeyboardItem
import com.github.ehsannarmani.model.method.*
import com.github.ehsannarmani.model.method.command.Commands
import com.github.ehsannarmani.model.updating_messages.EditTextMessage
import com.github.ehsannarmani.model.updating_messages.MessageReplyMarkup
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
                } else if (update.message?.text?.contains("/poll") == true) {
                    if (update.message.from.id.toInt() == 867396070) {
                        val res = bot.sendPoll(
                            PollMessage(
                            chatId = update.message.chat.id.toString(),
                            question = "this is test",
                            options = listOf("test1","test2")
                        )
                        )
//                        bot.sendMessage(
//                            TextMessage(
//                                text = "desc set: ${res?.result}" ,
//                                chatId = update.message.chat.id.toString(),
//                                parseMode = "html"
//                            )
//                        )
                    }
                }else if (update.message?.text?.contains("/dlt") == true) {
                    if (update.message.from.id.toInt() == 867396070) {
                        val res = bot.deleteMessage(
                                chatId = update.message.chat.id.toString(),
                                messageId = update.message.replyToMessage?.messageId ?: 0
                        )
//                        bot.sendMessage(
//                            TextMessage(
//                                text = "desc set: ${res?.result}" ,
//                                chatId = update.message.chat.id.toString(),
//                                parseMode = "html"
//                            )
//                        )
                    }
                }
                if (update.callbackQuery != null) {
                    bot.editMessageText(
                        EditTextMessage(
                            chatId = update.callbackQuery.message.chat.id.toString(),
                            text = "edited",
                            messageId = update.callbackQuery.message.messageId,
                            keyboard = InlineKeyboard(keyboard = listOf(listOf(InlineKeyboardItem("test2", "test2"))))
                        )
                    )
                    bot.answerCallbackQuery(
                        CallbackQuery(
                            id = update.callbackQuery.id,
                            text = "This is callback query!",
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