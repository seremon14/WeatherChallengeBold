package com.weatherchallengebold.domain.usecase

import com.weatherchallengebold.domain.model.WeatherForecast
import com.weatherchallengebold.domain.repository.WeatherRepository
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend fun execute(locationName: String, days: Int = 3): Result<WeatherForecast> {
        if (locationName.isBlank()) {
            return Result.failure(IllegalArgumentException("Location name cannot be blank"))
        }
        return repository.getForecast(locationName, days)
    }
}
