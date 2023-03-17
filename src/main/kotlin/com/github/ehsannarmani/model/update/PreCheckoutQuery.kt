package com.github.ehsannarmani.model.update

import com.github.ehsannarmani.model.message.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PreCheckoutQuery(
    @SerialName("id")
    val id:String,
    @SerialName("from")
    val from:User,
    @SerialName("currency")
    val currency:String,
    @SerialName("total_amount")
    val totalAmount:Long,
    @SerialName("invoice_payload")
    val invoicePayload:String,
    @SerialName("shipping_option_id")
    val shippingOptionId:String? = null,
    @SerialName("order_info")
    val orderInfo: OrderInfo? = null,
)
@Serializable
data class OrderInfo(
    @SerialName("name")
    val name:String? = null,
    @SerialName("phone_number")
    val phoneNumber:String? = null,
    @SerialName("email")
    val email:String? = null,
    @SerialName("shipping_address")
    val shippingAddress:ShippingAddress? = null,
)
