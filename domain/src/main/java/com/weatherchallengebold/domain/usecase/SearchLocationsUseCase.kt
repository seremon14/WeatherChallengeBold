package com.weatherchallengebold.domain.usecase

import com.weatherchallengebold.domain.model.Location
import com.weatherchallengebold.domain.repository.WeatherRepository
import javax.inject.Inject

class SearchLocationsUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend fun execute(query: String): Result<List<Location>> {
        if (query.isBlank()) {
            return Result.success(emptyList())
        }
        return repository.searchLocations(query)
    }
}
