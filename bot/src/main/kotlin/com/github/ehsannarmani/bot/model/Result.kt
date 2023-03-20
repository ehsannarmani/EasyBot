package com.github.ehsannarmani.bot.model

import com.github.ehsannarmani.bot.model.update.Message
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Result<T>(
    @SerialName("ok")
    val ok:Boolean,
    @SerialName("result")
    val result:T?=null,
    @SerialName("error_code")
    val errorCode:Int? = null,
    @SerialName("description")
    val description:String? = null
)
