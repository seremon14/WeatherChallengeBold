package com.weatherchallengebold.ui.forecast

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

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
                Text(text = state.error ?: "Error desconocido")
            }
        }
        state.forecast != null -> {
            ForecastContent(forecast = state.forecast!!)
        }
    }
}

@Composable
fun ForecastContent(forecast: com.weatherchallengebold.domain.model.WeatherForecast) {
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
                    date = day.date,
                    avgTemp = day.day.avgtempC,
                    condition = day.day.condition,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
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
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Clima Actual",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            AsyncImage(
                model = current.condition.icon,
                contentDescription = current.condition.text,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(text = "${current.tempC}°C")
            Text(text = current.condition.text)
            Text(text = "Humedad: ${current.humidity}%")
            Text(text = "Viento: ${current.windKph} km/h")
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
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = date,
                fontWeight = FontWeight.Bold
            )
            AsyncImage(
                model = condition.icon,
                contentDescription = condition.text
            )
            Text(text = "${avgTemp}°C")
            Text(text = condition.text)
        }
    }
}

