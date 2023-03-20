package com.github.ehsannarmani.bot.model.method

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CallbackQuery(
    @SerialName("callback_query_id")
    val id:String,
    @SerialName("text")
    val text:String? = null,
    @SerialName("show_alert")
    val showAlert:Boolean = false,
    @SerialName("url")
    val url:String? = null,
    @SerialName("cache_time")
    val cacheTime:Int? = null
)
