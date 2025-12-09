package com.weatherchallengebold.domain.model

data class ForecastDay(
    val date: String,
    val day: DayForecast,
    val hour: List<HourForecast>
)

data class DayForecast(
    val maxtempC: Double,
    val mintempC: Double,
    val avgtempC: Double,
    val condition: WeatherCondition,
    val maxwindKph: Double,
    val totalprecipMm: Double,
    val avghumidity: Int,
    val dailyChanceOfRain: Int
)

data class HourForecast(
    val time: String,
    val tempC: Double,
    val condition: WeatherCondition,
    val humidity: Int,
    val windKph: Double
)
