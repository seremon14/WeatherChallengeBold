package com.weatherchallengebold.ui.forecast

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.weatherchallengebold.domain.model.CurrentWeather
import com.weatherchallengebold.domain.model.Forecast
import com.weatherchallengebold.domain.model.Location
import com.weatherchallengebold.domain.model.WeatherCondition
import com.weatherchallengebold.domain.model.WeatherForecast
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ForecastScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun forecastScreen_displaysLocationName() {
        val forecast = WeatherForecast(
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

        composeTestRule.setContent {
            ForecastContent(forecast = forecast)
        }

        composeTestRule.onNodeWithText("Medellin, Colombia").assertIsDisplayed()
    }
}

