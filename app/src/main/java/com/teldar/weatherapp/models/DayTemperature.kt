package com.teldar.weatherapp.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DayTemperature(
    @SerialName("maxtemp_c") val maxTempC: Double,
    @SerialName("mintemp_c") val minTempC: Double,
    @SerialName("avgtemp_c") val avgTempC: Double,
    @SerialName("maxwind_kph") val maxWindKPH: Double,
    @SerialName("condition") val condition: Condition
)