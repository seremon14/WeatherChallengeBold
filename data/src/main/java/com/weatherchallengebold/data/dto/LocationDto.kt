package com.weatherchallengebold.data.dto

import com.weatherchallengebold.domain.model.Location

data class LocationDto(
    val id: Int,
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val url: String? = null
) {
    fun toDomain(): Location {
        return Location(
            id = id,
            name = name,
            region = region,
            country = country,
            lat = lat,
            lon = lon,
            url = url ?: "${name.lowercase()}-${region.lowercase()}-${country.lowercase()}"
        )
    }
}
