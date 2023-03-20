package com.github.ehsannarmani.bot.model.message.keyboard

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoveKeyboard(
    @SerialName("remove_keyboard")
    val removeKeyboard: Boolean,
    @SerialName("selective")
    val selective:Boolean? = null
):Keyboard()
