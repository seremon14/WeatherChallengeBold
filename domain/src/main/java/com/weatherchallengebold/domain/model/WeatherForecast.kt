package com.weatherchallengebold.domain.model

data class WeatherForecast(
    val location: Location,
    val current: CurrentWeather,
    val forecast: Forecast
)

data class Forecast(
    val forecastday: List<ForecastDay>
)
