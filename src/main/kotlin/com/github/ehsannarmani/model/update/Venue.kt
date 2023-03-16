package com.github.ehsannarmani.model.update

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Venue(
    @SerialName("location")
    val location: Location,
    @SerialName("title")
    val title:String,
    @SerialName("address")
    val address:String
)
