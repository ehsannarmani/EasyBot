package com.github.ehsannarmani.model.message.keyboard

import com.github.ehsannarmani.model.message.keyboard.inline.InlineKeyboardItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InlineKeyboard(
    @SerialName("inline_keyboard")
    val keyboard:List<List<InlineKeyboardItem>>? = null,
): Keyboard()