package com.weatherchallengebold.domain.usecase

import com.weatherchallengebold.domain.model.Location
import com.weatherchallengebold.domain.repository.WeatherRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SearchLocationsUseCaseTest {
    private lateinit var repository: WeatherRepository
    private lateinit var useCase: SearchLocationsUseCase

    @Before
    fun setup() {
        repository = mockk()
        useCase = SearchLocationsUseCase(repository)
    }

    @Test
    fun `execute with blank query returns empty list`() = runTest {
        val result = useCase.execute("")
        assertTrue(result.isSuccess)
        assertEquals(emptyList<Location>(), result.getOrNull())
    }

    @Test
    fun `execute with valid query returns locations`() = runTest {
        val expectedLocations = listOf(
            Location(1, "Medellin", "Antioquia", "Colombia", 6.29, -75.54, "medellin")
        )
        coEvery { repository.searchLocations("Medellin") } returns Result.success(expectedLocations)

        val result = useCase.execute("Medellin")

        assertTrue(result.isSuccess)
        assertEquals(expectedLocations, result.getOrNull())
    }

    @Test
    fun `execute with repository error returns failure`() = runTest {
        val exception = Exception("Network error")
        coEvery { repository.searchLocations("Test") } returns Result.failure(exception)

        val result = useCase.execute("Test")

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}

