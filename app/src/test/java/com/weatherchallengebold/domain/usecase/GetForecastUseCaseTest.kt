package com.weatherchallengebold.domain.usecase

import com.weatherchallengebold.domain.model.CurrentWeather
import com.weatherchallengebold.domain.model.Forecast
import com.weatherchallengebold.domain.model.Location
import com.weatherchallengebold.domain.model.WeatherCondition
import com.weatherchallengebold.domain.model.WeatherForecast
import com.weatherchallengebold.domain.repository.WeatherRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetForecastUseCaseTest {
    private lateinit var repository: WeatherRepository
    private lateinit var useCase: GetForecastUseCase

    @Before
    fun setup() {
        repository = mockk()
        useCase = GetForecastUseCase(repository)
    }

    @Test
    fun `execute with blank location name returns failure`() = runTest {
        val result = useCase.execute("")

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is IllegalArgumentException)
    }

    @Test
    fun `execute with valid location returns forecast`() = runTest {
        val expectedForecast = WeatherForecast(
            location = Location(1, "Medellin", "Antioquia", "Colombia", 6.29, -75.54, "medellin"),
            current = CurrentWeather(
                lastUpdated = "2025-12-07 13:45",
                tempC = 22.3,
                condition = WeatherCondition("Partly cloudy", "icon.png", 1003),
                humidity = 53,
                windKph = 3.6
            ),
            forecast = Forecast(forecastday = emptyList())
        )
        coEvery { repository.getForecast("Medellin", 3) } returns Result.success(expectedForecast)

        val result = useCase.execute("Medellin", 3)

        assertTrue(result.isSuccess)
        assertEquals(expectedForecast, result.getOrNull())
    }

    @Test
    fun `execute with repository error returns failure`() = runTest {
        val exception = Exception("Network error")
        coEvery { repository.getForecast("Test", 3) } returns Result.failure(exception)

        val result = useCase.execute("Test", 3)

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}

