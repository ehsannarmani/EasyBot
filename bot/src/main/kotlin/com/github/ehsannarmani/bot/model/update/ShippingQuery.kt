package com.github.ehsannarmani.bot.model.update

import com.github.ehsannarmani.bot.model.message.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShippingQuery(
    @SerialName("id")
    val id:String,
    @SerialName("from")
    val from:User,
    @SerialName("invoice_payload")
    val invoicePayload:String,
    @SerialName("shipping_address")
    val shippingAddress:ShippingAddress
)

@Serializable
data class ShippingAddress(
    @SerialName("country_code")
    val countryCode:String,
    @SerialName("state")
    val state:String,
    @SerialName("city")
    val city:String,
    @SerialName("street_line1")
    val streetLine1:String,
    @SerialName("street_line2")
    val streetLine2:String,
    @SerialName("post_code")
    val postCode:String,
)
