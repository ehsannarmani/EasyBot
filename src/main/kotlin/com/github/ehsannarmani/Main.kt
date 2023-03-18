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
    val bot = Bot(
        token = Constants.TOKEN,
        onUpdate = { update->
            onMessage { msg->
                onText {
                    if (it == "some"){
                        update.message.delete()
                    }else if (it == "hi"){
                       reply("How i can help you?")
                    }
                }
            }
            onCallbackQuery {
                when(it.data){
                    "yes"-> it.message.editText(text = "yes pressed")
                    "no"-> {
                        it.answer("You pressed no, so this message will be delete")
                        it.message.delete()
                    }
                    "ed"->{
                        it.message.editKeyboard(newKeyboard = InlineKeyboard(
                            listOf(
                                listOf(
                                    InlineKeyboardItem(text = "this is new keyboard", callbackData = "data")
                                )
                            )
                        ))
                    }
                }
            }
        }
    )

    bot.launch(
        webhookUrl = "https://76a0-94-131-98-78.eu.ngrok.io/bot",
        post = 3002
    )

}