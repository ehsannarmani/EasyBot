package com.github.ehsannarmani.bot.model.method.command

import com.github.ehsannarmani.bot.model.method.command.scope.BotCommandScope
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AddCommands(
    @SerialName("commands")
    val commands: List<BotCommand>,
    @SerialName("scope")
    val scope: BotCommandScope? = null,
    @SerialName("language_code")
    val languageCode:String? = null
)
@Serializable
data class BotCommand(
    @SerialName("command")
    val command:String,
    @SerialName("description")
    val description:String
)


