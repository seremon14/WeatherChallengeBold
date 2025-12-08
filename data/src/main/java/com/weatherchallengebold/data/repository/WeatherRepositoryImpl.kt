package com.weatherchallengebold.data.repository

import com.weatherchallengebold.data.api.WeatherApiService
import com.weatherchallengebold.domain.model.Location
import com.weatherchallengebold.domain.model.WeatherForecast
import com.weatherchallengebold.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: WeatherApiService,
    private val apiKey: String
) : WeatherRepository {
    override suspend fun searchLocations(query: String): Result<List<Location>> {
        return try {
            val locations = apiService.searchLocations(apiKey, query)
            Result.success(locations.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getForecast(locationName: String, days: Int): Result<WeatherForecast> {
        return try {
            val forecast = apiService.getForecast(apiKey, locationName, days)
            Result.success(forecast.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

