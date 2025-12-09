package com.weatherchallengebold.ui.search

import com.weatherchallengebold.domain.model.Location

data class SearchState(
    val query: String = "",
    val locations: List<Location> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
