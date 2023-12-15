package com.teldar.weatherapp.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Condition(
    @SerialName("text") val text: String,
    @SerialName("code") val code: Int
)