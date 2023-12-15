package com.teldar.weatherapp.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeather(
    @SerialName("is_day") val isDay: Int,
    @SerialName("temp_c") val tempC: Double,
    @SerialName("feelslike_c") val feelLikeC: Double,
    @SerialName("condition") val condition: Condition
)