package com.weatherchallengebold.data.dto

import com.weatherchallengebold.domain.model.Forecast
import com.weatherchallengebold.domain.model.WeatherForecast

data class WeatherForecastDto(
    val location: LocationDto,
    val current: CurrentWeatherDto,
    val forecast: ForecastDto
) {
    fun toDomain(): WeatherForecast {
        return WeatherForecast(
            location = location.toDomain(),
            current = current.toDomain(),
            forecast = forecast.toDomain()
        )
    }
}

data class ForecastDto(
    val forecastday: List<ForecastDayDto>
) {
    fun toDomain(): Forecast {
        return Forecast(
            forecastday = forecastday.map { it.toDomain() }
        )
    }
}
