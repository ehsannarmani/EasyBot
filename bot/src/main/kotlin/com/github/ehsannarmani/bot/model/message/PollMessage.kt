package com.github.ehsannarmani.bot.model.message

import com.github.ehsannarmani.bot.model.message.keyboard.Keyboard
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PollMessage(
    @SerialName("chat_id")
    val chatId:String,
    @SerialName("message_thread_id")
    val messageThreadId:Int? = null,
    @SerialName("question")
    val question:String,
    @SerialName("options")
    val options:List<String>,
    @SerialName("is_anonymous")
    val isAnonymous:Boolean? = null,
    @SerialName("type")
    val type:String? = null,
    @SerialName("allows_multiple_answers")
    val allowsMultipleAnswers:Boolean? = null,
    @SerialName("correct_option_id")
    val correctOptionId:Int? = null,
    @SerialName("explanation")
    val explanation:String? = null,
    @SerialName("explanation_parse_mode")
    val explanationParseMode:String = "markdown",
    @SerialName("explanation_entities")
    val explanationEntities:List<MessageEntity>? = null,
    @SerialName("open_period")
    val openPeriod:Int? = null,
    @SerialName("close_date")
    val closeDate:Int? = null,
    @SerialName("is_closed")
    val isClosed:Boolean? = null,
    @SerialName("disable_notification")
    val disableNotification: Boolean = false,
    @SerialName("protect_content")
    val protectContent: Boolean = false,
    @SerialName("reply_to_message_id")
    val replyToMessageId: Int? = null,
    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,
    @SerialName("reply_markup")
    val keyboard: Keyboard? = null
)
