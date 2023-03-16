package com.github.ehsannarmani.model.update

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @SerialName("latitude")
    val latitude:Double,
    @SerialName("longitude")
    val longitude:Double,
)
