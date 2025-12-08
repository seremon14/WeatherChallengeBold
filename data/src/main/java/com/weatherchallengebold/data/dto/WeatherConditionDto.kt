package com.weatherchallengebold.data.dto

import com.weatherchallengebold.domain.model.WeatherCondition

data class WeatherConditionDto(
    val text: String,
    val icon: String,
    val code: Int
) {
    fun toDomain(): WeatherCondition {
        return WeatherCondition(
            text = text,
            icon = if (icon.startsWith("//")) "https:$icon" else icon,
            code = code
        )
    }
}

