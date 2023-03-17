package com.github.ehsannarmani.model.method

import com.github.ehsannarmani.model.message.keyboard.WebAppInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ChatMenuButton(
    @SerialName("chat_id")
    val chatId: Long? = null,
    @SerialName("menu_button")
    val menuButton: ChatMenuButton? = null
)

@Serializable
sealed class MenuButton


@Serializable
@SerialName("commands")
data class MenuButtonCommands(@SerialName("type") val type: String = "commands") : MenuButton()

@Serializable
@SerialName("web_app")
data class MenuButtonWebApp(
    @SerialName("type")
    val type: String = "web_app",
    @SerialName("text")
    val text:String,
    @SerialName("web_app")
    val webAppInfo: WebAppInfo
) : MenuButton()

@Serializable
@SerialName("default")
data class MenuButtonDefault(
    @SerialName("type")
    val type: String = "default",
) : MenuButton()
