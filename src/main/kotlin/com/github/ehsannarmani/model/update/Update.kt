package com.github.ehsannarmani.model.update

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Update(
    @SerialName("update_id")
    val updateId:Long,
    @SerialName("message")
    val message: Message? = null,
    @SerialName("callback_query")
    val callbackQuery: CallbackQuery? = null,
    @SerialName("inline_query")
    val inlineQuery: InlineQuery? = null,
    @SerialName("my_chat_member")
    val myChatMember: MyChatMember? = null,
    @SerialName("poll_answer")
    val pollAnswer: PollAnswer? = null,
    var updateType: UpdateType = UpdateType.Message
){
    init {
        updateType = if (message != null){
            UpdateType.Message
        }else if (callbackQuery != null){
            UpdateType.CallbackQuery
        }else if (inlineQuery != null){
            UpdateType.InlineQuery
        }else if (myChatMember != null){
            UpdateType.ChatMember
        }else{
            UpdateType.PollAnswer
        }
    }
}

enum class UpdateType{
    Message,CallbackQuery,InlineQuery,ChatMember,PollAnswer
}