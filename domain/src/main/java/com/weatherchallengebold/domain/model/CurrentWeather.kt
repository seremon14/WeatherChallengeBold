package com.weatherchallengebold.domain.model

data class CurrentWeather(
    val lastUpdated: String,
    val tempC: Double,
    val condition: WeatherCondition,
    val humidity: Int,
    val windKph: Double
)

