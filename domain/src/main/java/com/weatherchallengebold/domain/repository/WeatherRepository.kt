package com.weatherchallengebold.domain.repository

import com.weatherchallengebold.domain.model.Location
import com.weatherchallengebold.domain.model.WeatherForecast

interface WeatherRepository {
    suspend fun searchLocations(query: String): Result<List<Location>>
    suspend fun getForecast(locationName: String, days: Int): Result<WeatherForecast>
}

