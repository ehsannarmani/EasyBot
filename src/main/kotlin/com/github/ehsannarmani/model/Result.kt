package com.github.ehsannarmani.model

import com.github.ehsannarmani.model.update.Message
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Result(
    @SerialName("ok")
    val ok:Boolean,
    @SerialName("result")
    val result:Message?=null,
    @SerialName("error_code")
    val errorCode:Int? = null,
    @SerialName("description")
    val description:String? = null
)
