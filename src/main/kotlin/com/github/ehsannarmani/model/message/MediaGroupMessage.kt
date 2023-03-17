package com.github.ehsannarmani.model.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MediaGroupMessage(
    @SerialName("chat_id")
    val chatId:String,
    @SerialName("media")
    val media:List<InputMedia>,
    @SerialName("message_thread_id")
    val messageThreadId:Int? = null,
    @SerialName("disable_notification")
    val disableNotification:Boolean? = null,
    @SerialName("protect_content")
    val protectContent:Boolean? = null,
    @SerialName("reply_to_message_id")
    val replyToMessageId:Int? = null,
    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply:Boolean? = null,
)


@Serializable
sealed class InputMedia()


@Serializable
data class InputMediaAudio(
    @SerialName("media")
    val media:String,
    @SerialName("thumbnail")
    val thumbnail:String? = null,
    @SerialName("caption")
    val caption:String? = null,
    @SerialName("parse_mode")
    val parseMode:String? = null,
    @SerialName("caption_entities")
    val captionEntities:List<MessageEntity>? = null,
    @SerialName("duration")
    val duration:Int? = null,
    @SerialName("performer")
    val performer:String? = null,
    @SerialName("title")
    val title:String? = null,
):InputMedia()
@Serializable
data class InputMediaDocument(
    @SerialName("media")
    val media:String,
    @SerialName("thumbnail")
    val thumbnail:String? = null,
    @SerialName("caption")
    val caption:String? = null,
    @SerialName("parse_mode")
    val parseMode:String? = null,
    @SerialName("caption_entities")
    val captionEntities:List<MessageEntity>? = null,
    @SerialName("disable_content_type_detection")
    val disableContentTypeDetection:Boolean? = null,
):InputMedia()
@Serializable
data class InputMediaPhoto(
    @SerialName("media")
    val media:String,
    @SerialName("caption")
    val caption:String? = null,
    @SerialName("parse_mode")
    val parseMode:String? = null,
    @SerialName("caption_entities")
    val captionEntities:List<MessageEntity>? = null,
    @SerialName("has_spoiler")
    val hasSpoiler:Boolean? = null,
):InputMedia()
@Serializable
data class InputMediaVideo(
    @SerialName("media")
    val media:String,
    @SerialName("thumbnail")
    val thumbnail:String? = null,
    @SerialName("caption")
    val caption:String? = null,
    @SerialName("parse_mode")
    val parseMode:String? = null,
    @SerialName("width")
    val width:Int? = null,
    @SerialName("height")
    val height:Int? = null,
    @SerialName("duration")
    val duration:Int? = null,
    @SerialName("supports_streaming")
    val supportStreaming:Boolean? = null,
    @SerialName("caption_entities")
    val captionEntities:List<MessageEntity>? = null,
    @SerialName("has_spoiler")
    val hasSpoiler:Boolean? = null,
):InputMedia()