package com.github.ehsannarmani

import com.github.ehsannarmani.model.message.*
import com.github.ehsannarmani.model.message.keyboard.InlineKeyboard
import com.github.ehsannarmani.model.message.keyboard.inline.InlineKeyboardItem
import com.github.ehsannarmani.model.method.*
import com.github.ehsannarmani.model.method.command.Commands
import com.github.ehsannarmani.model.method.inline_query.AnswerInlineQuery
import com.github.ehsannarmani.model.method.inline_query.InputMessageContent
import com.github.ehsannarmani.model.method.inline_query.result.InlineQueryResultArticle
import com.github.ehsannarmani.model.method.inline_query.result.InlineQueryResultContact
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
        onUpdate = { update ->

            
            scope.launch {
                if (update.message?.text == "/test") {
                    sendMessage(
                        TextMessage(
                            text = "Im ${getMe()?.result?.firstName}",
                            chatId = update.message.chat.id.toString(),
                            parseMode = "html"
                        )
                    )
                } else if (update.message?.text?.contains("/poll") == true) {
                    if (update.message.from.id.toInt() == 867396070) {
                        val res = sendPoll(
                            PollMessage(
                                chatId = update.message.chat.id.toString(),
                                question = "this is test",
                                options = listOf("test1", "test2")
                            )
                        )

                    }
                } else if (update.message?.text?.contains("/invoice") == true) {
                    if (update.message.from.id.toInt() == 867396070) {
                        val res = createInvoiceLink(
                            Invoice(
                                title = "invoice test",
                                description = "invoice desc",
                                currency = "ILS",
                                payload = "test",
                                prices = listOf(
                                    LabeledPrice("price1", 15000),
                                    LabeledPrice("price2", 1000)
                                ),
                                providerToken = "1877036958:TEST:35362ef782e7628e7955e58afd2524a98bbc3734"
                            )
                        )
                        sendMessage(
                            TextMessage(
                                text = "invoice created: ${res?.result}",
                                chatId = update.message.chat.id.toString(),
                                parseMode = "html"
                            )
                        )



                    }
                }

                if (update.inlineQuery != null) {
                    answerInlineQuery(
                        AnswerInlineQuery(
                            inlineQueryId = update.inlineQuery.id,
                            results = listOf(
                                InlineQueryResultArticle(
                                    title = "this is title",
                                    description = "this is description",
                                    inputMessageContent = InputMessageContent(
                                        messageText = "here is test"
                                    ),
                                    keyboard = InlineKeyboard(
                                        keyboard = listOf(listOf(InlineKeyboardItem("test", "test")))
                                    )
                                )
                            )
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
        webhookUrl = "https://1c01-94-131-98-78.eu.ngrok.io/bot",
        post = 3002
    )

}