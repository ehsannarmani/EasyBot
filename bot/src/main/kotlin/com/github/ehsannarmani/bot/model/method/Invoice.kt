package com.github.ehsannarmani.bot.model.method

import com.github.ehsannarmani.bot.model.message.keyboard.Keyboard
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Invoice(
    @SerialName("chat_id")
    val chatId:String? = null,
    @SerialName("message_thread_id")
    val messageThreadId:Int? = null,
    @SerialName("title")
    val title:String,
    @SerialName("description")
    val description:String,
    @SerialName("payload")
    val payload:String,
    @SerialName("provider_token")
    val providerToken:String,
    @SerialName("currency")
    val currency:String,
    @SerialName("prices")
    val prices:List<LabeledPrice>,
    @SerialName("max_tip_amount")
    val maxTipAmount:Int? = null,
    @SerialName("suggested_tip_amounts")
    val suggestedTipAmounts:List<Int>? = null,
    @SerialName("start_parameter")
    val startParameter:String? = null,
    @SerialName("provider_data")
    val providerData:String? = null,
    @SerialName("photo_url")
    val photoUrl:String? = null,
    @SerialName("photo_size")
    val photoSize:Int? = null,
    @SerialName("photo_width")
    val photoWidth:Int? = null,
    @SerialName("photo_height")
    val photoHeight:Int? = null,
    @SerialName("need_name")
    val needName:Boolean? = null,
    @SerialName("need_phone_number")
    val needPhoneNumber:Boolean? = null,
    @SerialName("need_email")
    val needEmail:Boolean? = null,
    @SerialName("need_shipping_address")
    val needShippingAddress:Boolean? = null,
    @SerialName("send_phone_number_to_provider")
    val sendPhoneNumberToProvider:Boolean? = null,
    @SerialName("send_email_to_provider")
    val sendEmailToProvider:Boolean? = null,
    @SerialName("is_flexible")
    val isFlexible:Boolean? = null,
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


@Serializable
data class LabeledPrice(
    @SerialName("label") val label:String,
    @SerialName("amount") val amount:Int
)
