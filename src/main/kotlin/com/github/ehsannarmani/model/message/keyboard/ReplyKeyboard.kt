package com.github.ehsannarmani.model.message.keyboard

import com.github.ehsannarmani.model.message.keyboard.reply.ReplyKeyboardItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReplyKeyboard(
    @SerialName("keyboard")
    val keyboard:List<List<ReplyKeyboardItem>>? = null,
    @SerialName("is_persistent")
    val isPersistent:Boolean = false,
    @SerialName("resize_keyboard")
    val resizeKeyboard:Boolean = false,
    @SerialName("one_time_keyboard")
    val oneTimeKeyboard:Boolean = false,
    @SerialName("input_field_placeholder")
    val inputFieldPlaceholder:String? = "",
    @SerialName("selective")
    val selective:Boolean = false,
): Keyboard()