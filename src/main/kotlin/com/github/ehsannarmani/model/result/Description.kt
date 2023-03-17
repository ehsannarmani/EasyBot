package com.github.ehsannarmani.model.result

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Description(@SerialName("description") val description:String,)
