package com.github.ehsannarmani

import com.github.ehsannarmani.model.message.*
import com.github.ehsannarmani.model.message.keyboard.InlineKeyboard
import com.github.ehsannarmani.model.message.keyboard.ReplyKeyboard
import com.github.ehsannarmani.model.message.keyboard.inline.InlineKeyboardItem
import com.github.ehsannarmani.model.message.keyboard.reply.ReplyKeyboardItem
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
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Grades(
    @SerialName("grades") val grades:List<Int>,
    @SerialName("parent") val parent:String
)

fun main() {
    val bot = Bot(
        token = Constants.TOKEN,
        onUpdate = { update ->
            onMessage { msg->
                onText("db"){
                    msg.from.putData(
                        key = "grades",
                        data = Grades(grades = listOf(1,2,3), parent = "ehsan")
                    )
                    msg.from.putData(
                        key = "grades_list",
                        data = arrayListOf(
                            Grades(grades = listOf(1,2,3), parent = "ehsan"),
                            Grades(grades = listOf(3,4,5), parent = "ali")
                        )
                    )
                    msg.from.putData(
                        key = "name",
                        data = "ehsan"
                    )
                    msg.from.putData(
                        key = "age",
                        data = 18
                    )
                    msg.from.putData(
                        key = "isDead",
                        data = false
                    )
                }
                onText("get"){
                    val grades = msg.from.getData<Grades>("grades")
                    val listOfGrades = msg.from.getData<ArrayList<Grades>>("grades_list")
                    val name = msg.from.getData<String>("name")
                    val age = msg.from.getData<Int>("age")
                    val isDead = msg.from.getData<Boolean>("isDead")
                    println("\n\n$grades\n$listOfGrades\n$name\n$age\n$isDead")
                }
            }

        }
    )

    bot.launch(
        webhookUrl = "https://5591-94-131-98-78.eu.ngrok.io/bot",
        post = 3002
    )

}