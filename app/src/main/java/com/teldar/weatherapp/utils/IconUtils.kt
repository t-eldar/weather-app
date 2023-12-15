package com.teldar.weatherapp.utils

import androidx.annotation.DrawableRes
import com.teldar.weatherapp.R

@DrawableRes
fun getIcon(code: Int, isDay: Boolean): Int {
    return if (isDay) when (code) {
        1000 -> R.drawable.day
        1063, 1150, 1153, 1168, 1171, 1180, 1183, 1186, 1192, 1195, 1198, 1201, 1237, 1240, 1243, 1246, 1249, 1252, 1261, 1264 -> R.drawable.rainy
        1066, 1069, 1072, 1114, 1117, 1147, 1204, 1207, 1210, 1213, 1216, 1219, 1222, 1225, 1255, 1258 -> R.drawable.snowy
        1030, 1135 -> R.drawable.mist_day
        1087, 1273, 1276, 1279, 1282 -> R.drawable.thunder
        1003, 1006, 1009 -> R.drawable.cloudy_day
        else -> R.drawable.cloudy_day
    } else when (code) {
        1000 -> R.drawable.night
        1063, 1150, 1153, 1168, 1171, 1180, 1183, 1186, 1192, 1195, 1198, 1201, 1237, 1240, 1243, 1246, 1249, 1252, 1261, 1264 -> R.drawable.rainy
        1066, 1069, 1072, 1114, 1117, 1147, 1204, 1207, 1210, 1213, 1216, 1219, 1222, 1225, 1255, 1258 -> R.drawable.snowy
        1030, 1135 -> R.drawable.mist_night
        1087, 1273, 1276, 1279, 1282 -> R.drawable.thunder
        1003, 1006, 1009 -> R.drawable.cloudy_night
        else -> R.drawable.cloudy_night
    }
}