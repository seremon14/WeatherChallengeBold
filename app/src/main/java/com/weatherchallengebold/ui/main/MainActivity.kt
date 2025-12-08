package com.weatherchallengebold.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.weatherchallengebold.ui.forecast.ForecastScreen
import com.weatherchallengebold.ui.search.SearchScreen
import com.weatherchallengebold.ui.theme.WeatherChallengeBoldTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherChallengeBoldTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "search"
                    ) {
                        composable("search") {
                            SearchScreen(
                                onLocationSelected = { locationName ->
                                    navController.navigate("forecast/$locationName")
                                }
                            )
                        }
                        composable("forecast/{locationName}") { backStackEntry ->
                            ForecastScreen()
                        }
                    }
                }
            }
        }
    }
}
