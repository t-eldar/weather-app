package com.teldar.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.teldar.weatherapp.ui.screens.SearchScreen
import com.teldar.weatherapp.ui.screens.TodayWeatherScreen
import com.teldar.weatherapp.ui.screens.WeekWeatherScreen
import com.teldar.weatherapp.ui.theme.WeatherAppTheme
import com.teldar.weatherapp.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<MainViewModel>()

        setContent {
            WeatherAppTheme(darkTheme = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Scaffold(bottomBar = {
                        NavigationBar(modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer)) {
                            NavigationBarItem(
                                selected = false,
                                onClick = { navController.navigate("search") },
                                icon = {
                                    Image(
                                        painter = painterResource(id = R.drawable.search_icon),
                                        contentDescription = null,
                                        modifier = Modifier.size(50.dp)
                                    )
                                },
                                modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer)
                            )
                            NavigationBarItem(
                                selected = false,
                                onClick = { navController.navigate("today") },
                                icon = {
                                    Image(
                                        painter = painterResource(id = R.drawable.today_icon),
                                        contentDescription = null,
                                        modifier = Modifier.size(50.dp)
                                    )
                                },
                                modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer)
                            )
                            NavigationBarItem(
                                selected = false,
                                onClick = { navController.navigate("week") },
                                icon = {
                                    Image(
                                        painter = painterResource(id = R.drawable.forecast_icon),
                                        contentDescription = null,
                                        modifier = Modifier.size(50.dp)
                                    )
                                },
                                modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer)
                            )
                        }
                    }) { padding ->
                        NavHost(startDestination = "search", navController = navController) {
                            composable(route = "today") {
                                TodayWeatherScreen(
                                    viewModel = viewModel,
                                    modifier = Modifier.padding(padding)
                                )
                            }
                            composable(route = "week") {
                                WeekWeatherScreen(
                                    viewModel = viewModel,
                                    modifier = Modifier.padding(padding)
                                )
                            }
                            composable(route = "search") {
                                SearchScreen(
                                    viewModel = viewModel,
                                    navController = navController,
                                    modifier = Modifier.padding(padding)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

