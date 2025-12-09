package com.weatherchallengebold.ui.forecast

import com.weatherchallengebold.domain.model.WeatherForecast

data class ForecastState(
    val forecast: WeatherForecast? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
