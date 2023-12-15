package com.teldar.weatherapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.teldar.weatherapp.ui.theme.Typography
import com.teldar.weatherapp.ui.theme.WeatherAppTheme
import com.teldar.weatherapp.utils.getIcon
import com.teldar.weatherapp.viewmodels.MainViewModel
import com.teldar.weatherapp.viewmodels.UiState


@Composable
fun TodayWeatherScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val weatherResponse = viewModel.weather

    if (viewModel.uiState == UiState.Loading) {
        LoadingScreen()
        return
    }

    if (weatherResponse == null || viewModel.uiState == UiState.Error) {
        ErrorScreen(message = "Ошибка при получении погоды!")
        return
    }

    val city = weatherResponse.location.name
    val isDay = weatherResponse.current.isDay
    val min = weatherResponse.forecast.forecastDays.first().dayTemperature.minTempC
    val max = weatherResponse.forecast.forecastDays.first().dayTemperature.maxTempC
    val temp = weatherResponse.current.tempC
    val icon = getIcon(weatherResponse.current.condition.code, (isDay != 0))

    WeatherAppTheme(darkTheme = (isDay == 0)) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .background(MaterialTheme.colorScheme.background)

                .fillMaxHeight()
                .padding(top = 50.dp)
        ) {
            Text(
                text = city,
                style = Typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary,
            )
            Image(painter = painterResource(id = icon), contentDescription = null)
            Text(
                text = "$temp°", style = Typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary
            )
            Card(
                shape = ShapeDefaults.Medium,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier.padding(bottom = 30.dp)
            ) {
                Text(
                    text = "Погода сегодня",
                    style = Typography.titleSmall,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 20.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "от", style = Typography.titleSmall,
                            modifier = Modifier.padding(start = 20.dp)
                        )
                        Text(text = "$min°C", style = Typography.titleSmall)
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = "до", style = Typography.titleSmall,
                        )
                        Text(
                            text = "$max°C", style = Typography.titleSmall,
                            modifier = Modifier.padding(end = 20.dp)
                        )
                    }
                }
            }
        }
    }
}
