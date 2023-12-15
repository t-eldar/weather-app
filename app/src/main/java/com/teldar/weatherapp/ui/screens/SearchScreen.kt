package com.teldar.weatherapp.ui.screens

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.teldar.weatherapp.R
import com.teldar.weatherapp.ui.theme.Typography
import com.teldar.weatherapp.ui.theme.WeatherAppTheme
import com.teldar.weatherapp.utils.rememberImeState
import com.teldar.weatherapp.viewmodels.MainViewModel

@Composable
fun SearchScreen(
    viewModel: MainViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val imeState = rememberImeState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) {
            scrollState.animateScrollTo(scrollState.maxValue, tween(300))
        }
    }
    WeatherAppTheme {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(scrollState)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mist_day),
                    contentDescription = null,
                    modifier = Modifier.size(400.dp)
                )
                TextField(
                    textStyle = Typography.titleMedium,
                    value = viewModel.city,
                    onValueChange = { value -> viewModel.city = value },
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                Button(onClick = {
                    viewModel.getWeather()
                    navController.navigate(
                        "today"
                    )
                }) {
                    Text(text = "Найти", style = Typography.titleMedium)
                }
            }
        }
    }
}