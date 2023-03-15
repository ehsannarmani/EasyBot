package com.github.ehsannarmani.model.message.keyboard

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForceReply(
    @SerialName("force_reply")
    val forceReply:Boolean,
    @SerialName("input_field_placeholder")
    val inputFieldPlaceholder:String? = null,
    @SerialName("selective")
    val selective:Boolean? = null
):Keyboard()
