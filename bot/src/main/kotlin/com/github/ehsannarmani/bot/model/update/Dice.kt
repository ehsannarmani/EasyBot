package com.github.ehsannarmani.bot.model.update

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dice(
    @SerialName("emoji")
    val emoji:String,
    @SerialName("value")
    val value:Int,
)
