package com.weatherchallengebold.data.dto

import com.google.gson.annotations.SerializedName
import com.weatherchallengebold.domain.model.DayForecast
import com.weatherchallengebold.domain.model.ForecastDay
import com.weatherchallengebold.domain.model.HourForecast

data class ForecastDayDto(
    val date: String,
    val day: DayForecastDto,
    val hour: List<HourForecastDto>
) {
    fun toDomain(): ForecastDay {
        return ForecastDay(
            date = date,
            day = day.toDomain(),
            hour = hour.map { it.toDomain() }
        )
    }
}

data class DayForecastDto(
    @SerializedName("maxtemp_c")
    val maxtempC: Double,
    @SerializedName("mintemp_c")
    val mintempC: Double,
    @SerializedName("avgtemp_c")
    val avgtempC: Double,
    val condition: WeatherConditionDto,
    @SerializedName("maxwind_kph")
    val maxwindKph: Double,
    @SerializedName("totalprecip_mm")
    val totalprecipMm: Double,
    @SerializedName("avghumidity")
    val avghumidity: Int,
    @SerializedName("daily_chance_of_rain")
    val dailyChanceOfRain: Int
) {
    fun toDomain(): DayForecast {
        return DayForecast(
            maxtempC = maxtempC,
            mintempC = mintempC,
            avgtempC = avgtempC,
            condition = condition.toDomain(),
            maxwindKph = maxwindKph,
            totalprecipMm = totalprecipMm,
            avghumidity = avghumidity,
            dailyChanceOfRain = dailyChanceOfRain
        )
    }
}

data class HourForecastDto(
    val time: String,
    @SerializedName("temp_c")
    val tempC: Double,
    val condition: WeatherConditionDto,
    val humidity: Int,
    @SerializedName("wind_kph")
    val windKph: Double
) {
    fun toDomain(): HourForecast {
        return HourForecast(
            time = time,
            tempC = tempC,
            condition = condition.toDomain(),
            humidity = humidity,
            windKph = windKph
        )
    }
}

