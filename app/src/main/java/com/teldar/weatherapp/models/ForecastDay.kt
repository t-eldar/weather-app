package com.teldar.weatherapp.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDay(
    @SerialName("date") val date: String,
    @SerialName("day") val dayTemperature: DayTemperature,
)

