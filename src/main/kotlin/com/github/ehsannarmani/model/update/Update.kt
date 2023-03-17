package com.github.ehsannarmani.model.update

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Update(
    @SerialName("update_id")
    val updateId: Long,
    @SerialName("message")
    val message: Message? = null,
    @SerialName("edited_message")
    val editedMessage: Message? = null,
    @SerialName("channel_post")
    val channelPost: Message? = null,
    @SerialName("edited_channel_post")
    val editedChannelPost: Message? = null,
    @SerialName("chosen_inline_result")
    val chosenInlineResult: ChosenInlineResult? = null,
    @SerialName("callback_query")
    val callbackQuery: CallbackQuery? = null,
    @SerialName("shipping_query")
    val shippingQuery: ShippingQuery? = null,
    @SerialName("pre_checkout_query")
    val preCheckoutQuery: ShippingQuery? = null,
    @SerialName("inline_query")
    val inlineQuery: InlineQuery? = null,
    @SerialName("my_chat_member")
    val myChatMember: MyChatMember? = null,
    @SerialName("chat_join_request")
    val chatJoinRequest: ChatJoinRequest? = null,
    @SerialName("chat_member")
    val chatMember: MyChatMember? = null,
    @SerialName("poll_answer")
    val pollAnswer: PollAnswer? = null,
    @SerialName("poll")
    val poll: Poll? = null,
    var updateType: UpdateType = UpdateType.Message
) {
    init {
        updateType = if (message != null) {
            UpdateType.Message
        } else if (callbackQuery != null) {
            UpdateType.CallbackQuery
        } else if (inlineQuery != null) {
            UpdateType.InlineQuery
        } else if (myChatMember != null) {
            UpdateType.MyChatMember
        } else if (chatMember != null) {
            UpdateType.ChatMember
        } else if (editedMessage != null) {
            UpdateType.EditMessage
        } else if (channelPost != null) {
            UpdateType.ChannelPost
        } else if (editedChannelPost != null) {
            UpdateType.ChannelPostEdit
        } else if (chosenInlineResult != null) {
            UpdateType.InlineResult
        } else if (shippingQuery != null) {
            UpdateType.Shipping
        } else if (preCheckoutQuery != null) {
            UpdateType.PreCheckout
        } else if (poll != null) {
            UpdateType.Poll
        }  else if (chatJoinRequest != null) {
            UpdateType.JoinRequest
        } else {
            UpdateType.PollAnswer
        }
    }
}

enum class UpdateType {
    Message, EditMessage, ChannelPost, ChannelPostEdit, JoinRequest ,InlineResult, PreCheckout, Shipping, CallbackQuery, InlineQuery, ChatMember, MyChatMember, PollAnswer, Poll
}