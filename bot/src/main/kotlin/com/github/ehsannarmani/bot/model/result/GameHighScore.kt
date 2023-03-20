package com.github.ehsannarmani.bot.model.result

import com.github.ehsannarmani.bot.model.message.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GameHighScore(
    @SerialName("position")
    val position:Int,
    @SerialName("user")
    val user: User,
    @SerialName("score")
    val score:Int
)
