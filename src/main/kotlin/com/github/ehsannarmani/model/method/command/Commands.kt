package com.github.ehsannarmani.model.method.command

import com.github.ehsannarmani.model.method.command.scope.BotCommandScope
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Commands(
    @SerialName("scope")
    val scope: BotCommandScope? = null,
    @SerialName("language_code")
    val languageCode:String? = null
)
