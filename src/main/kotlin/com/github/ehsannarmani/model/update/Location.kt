package com.github.ehsannarmani.model.update

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @SerialName("latitude")
    val latitude:Double,
    @SerialName("longitude")
    val longitude:Double,
    @SerialName("horizontal_accuracy")
    val horizontalAccuracy:Double? = null,
    @SerialName("live_period")
    val livePeriod:Int? = null,
    @SerialName("heading")
    val heading:Int? = null,
    @SerialName("proximity_alert_radius")
    val proximityAlertRadius:Int? = null,
)
