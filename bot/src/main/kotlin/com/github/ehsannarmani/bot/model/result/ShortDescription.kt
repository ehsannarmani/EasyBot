package com.github.ehsannarmani.bot.model.result

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ShortDescription(@SerialName("short_description") val shortDescription:String,)
