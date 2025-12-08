package com.weatherchallengebold.data.dto

import com.google.gson.annotations.SerializedName
import com.weatherchallengebold.domain.model.CurrentWeather

data class CurrentWeatherDto(
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("temp_c")
    val tempC: Double,
    val condition: WeatherConditionDto,
    val humidity: Int,
    @SerializedName("wind_kph")
    val windKph: Double
) {
    fun toDomain(): CurrentWeather {
        return CurrentWeather(
            lastUpdated = lastUpdated,
            tempC = tempC,
            condition = condition.toDomain(),
            humidity = humidity,
            windKph = windKph
        )
    }
}

