package com.weatherchallengebold.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weatherchallengebold.domain.usecase.SearchLocationsUseCase
import com.weatherchallengebold.util.ErrorHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchLocationsUseCase: SearchLocationsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state.asStateFlow()
    private var searchJob: Job? = null

    fun onSearchQueryChanged(query: String) {
        searchLocations(query)
    }

    private fun searchLocations(query: String) {
        searchJob?.cancel()
        _state.update { it.copy(query = query, error = null) }
        if (query.isBlank()) {
            _state.update { it.copy(locations = emptyList(), isLoading = false) }
            return
        }
        searchJob = viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            delay(300)
            searchLocationsUseCase.execute(query).fold(
                onSuccess = { locations ->
                    _state.update {
                        it.copy(
                            locations = locations,
                            isLoading = false,
                            error = null
                        )
                    }
                },
                onFailure = { exception ->
                    ErrorHandler.logError(
                        "SearchViewModel",
                        "Error al buscar ubicaciones: $query",
                        exception
                    )
                    val errorMessage = ErrorHandler.getErrorMessage(exception)

                    _state.update {
                        it.copy(
                            locations = emptyList(),
                            isLoading = false,
                            error = errorMessage
                        )
                    }
                }
            )
        }
    }
}
