package com.github.ehsannarmani.model.update

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
    val replyToMessage: Message? = null,
    @SerialName("forward_from")
    val forwardFrom: From? = null,
    @SerialName("forward_date")
    val forwardDate:Long? = null,
    @SerialName("text")
    val text:String? = null,
    @SerialName("photo")
    val photo:List<Photo>? = null,
    @SerialName("sticker")
    val sticker: Sticker? = null,
    @SerialName("animation")
    val animation: Animation? = null,
    @SerialName("document")
    val document: Document? = null,
    @SerialName("audio")
    val audio: Audio? = null,
    @SerialName("voice")
    val voice: Voice? = null,
    @SerialName("video")
    val video: Video? = null,
    @SerialName("video_note")
    val videoNote: VideoNote? = null,
    @SerialName("poll")
    val poll: Poll? = null,
    @SerialName("caption")
    val caption:String? = null,
    @SerialName("new_chat_title")
    val newChatTitle:String? = null,
    @SerialName("has_protected_content")
    val hasProtectedContent:Boolean = false,
    @SerialName("contact")
    val contact: Contact? = null,
    @SerialName("location")
    val location: Location? = null,
    @SerialName("reply_markup")
    val replyMarkup:ReplyMarkup? = null,
    @SerialName("entities")
    val entities:List<Entity>? = null,
    @SerialName("via_bot")
    val viaBot:From? = null,
    @SerialName("has_media_spoiler")
    val hasMediaSpoiler:Boolean? = null,
    @SerialName("media_group_id")
    val mediaGroupId:String? = null,
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
        if (poll != null){
            messageType = MessageType.Poll
        }
    }
}

enum class MessageType{
    Text,Photo,Audio,Voice,Document,Gif,Sticker,Poll
}