package com.weatherchallengebold.ui.forecast

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.weatherchallengebold.util.DateFormatter

@Composable
fun ForecastScreen(
    viewModel: ForecastViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        state.error != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = state.error ?: "Unknown error")
            }
        }

        state.forecast != null -> {
            ForecastContent(forecast = state.forecast!!)
        }
    }
}

@Composable
fun ForecastContent(forecast: com.weatherchallengebold.domain.model.WeatherForecast) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.screenWidthDp > configuration.screenHeightDp

    if (isLandscape) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(end = 16.dp)
            ) {
                Text(
                    text = "${forecast.location.name}, ${forecast.location.country}",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                CurrentWeatherCard(current = forecast.current)
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Text(
                    text = "Pronóstico 3 días",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                LazyColumn {
                    items(forecast.forecast.forecastday) { day ->
                        ForecastDayCard(
                            date = DateFormatter.formatForecastDate(day.date),
                            avgTemp = day.day.avgtempC,
                            condition = day.day.condition,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                    }
                }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "${forecast.location.name}, ${forecast.location.country}",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            CurrentWeatherCard(current = forecast.current)

            Text(
                text = "Pronóstico 3 días",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            LazyRow {
                items(forecast.forecast.forecastday) { day ->
                    ForecastDayCard(
                        date = DateFormatter.formatForecastDate(day.date),
                        avgTemp = day.day.avgtempC,
                        condition = day.day.condition,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CurrentWeatherCard(current: com.weatherchallengebold.domain.model.CurrentWeather) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Clima Actual",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(text = "${current.tempC}°C")
                Text(text = current.condition.text)
                Text(text = "Humedad: ${current.humidity}%")
                Text(text = "Viento: ${current.windKph} km/h")
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                AsyncImage(
                    model = current.condition.icon,
                    contentDescription = current.condition.text,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .width(80.dp)
                        .align(Alignment.End)
                )
            }

        }
    }
}

@Composable
fun ForecastDayCard(
    date: String,
    avgTemp: Double,
    condition: com.weatherchallengebold.domain.model.WeatherCondition,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = date,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            AsyncImage(
                model = condition.icon,
                contentDescription = condition.text,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                text = "${avgTemp}°C",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(text = condition.text)
        }
    }
}
