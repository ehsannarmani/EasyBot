package com.github.ehsannarmani.bot.model.method

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ShippingQuery(
    @SerialName("shipping_query_id")
    val shippingQueryId:String,
    @SerialName("ok")
    val ok:Boolean,
    @SerialName("shipping_options")
    val shippingOptions:List<ShippingOption>,
    @SerialName("error_message")
    val errorMessage:String,
)


@Serializable
data class ShippingOption(
    @SerialName("id")
    val id:String,
    @SerialName("title")
    val title:String,
    @SerialName("prices")
    val prices:List<LabeledPrice>
)
