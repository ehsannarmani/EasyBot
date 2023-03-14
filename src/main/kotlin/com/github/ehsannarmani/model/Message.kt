package com.github.ehsannarmani.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Message(
    @SerialName("message_id")
    val messageId:Int,
    @SerialName("from")
    val from: From,
    @SerialName("chat")
    val chat: Chat,
    @SerialName("date")
    val date:Long,
    @SerialName("reply_to_message")
    val replyToMessage:Message? = null,
    @SerialName("forward_from")
    val forwardFrom:From? = null,
    @SerialName("forward_date")
    val forwardDate:Long? = null,
    @SerialName("text")
    val text:String? = null,
    @SerialName("photo")
    val photo:List<Photo>? = null,
    @SerialName("sticker")
    val sticker:Sticker? = null,
    @SerialName("animation")
    val animation:Animation? = null,
    @SerialName("document")
    val document:Document? = null,
    @SerialName("audio")
    val audio:Audio? = null,
    @SerialName("voice")
    val voice:Voice? = null,
    @SerialName("caption")
    val caption:String? = null,
    @SerialName("new_chat_title")
    val newChatTitle:String? = null,
    @SerialName("has_protected_content")
    val hasProtectedContent:Boolean = false,
    @SerialName("entities")
    val entities:List<Entity>? = null,
    var messageType: MessageType = MessageType.Text
){
    init {
        if (text != null){
            messageType = MessageType.Text
        }
        if (audio != null){
            messageType = MessageType.Audio
        }
        if (sticker != null){
            messageType = MessageType.Sticker
        }
        if (document != null){
            messageType = MessageType.Document
        }
        if (animation != null){
            messageType = MessageType.Gif
        }
        if (voice != null){
            messageType = MessageType.Voice
        }
        if (photo != null){
            messageType = MessageType.Photo
        }
    }
}

enum class MessageType{
    Text,Photo,Audio,Voice,Document,Gif,Sticker
}