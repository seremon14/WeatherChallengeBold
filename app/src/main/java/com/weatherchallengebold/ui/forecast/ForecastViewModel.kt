package com.weatherchallengebold.ui.forecast

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weatherchallengebold.domain.usecase.GetForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val getForecastUseCase: GetForecastUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val locationName: String = savedStateHandle.get<String>("locationName") ?: ""
    private val _state = MutableStateFlow(ForecastState())
    val state: StateFlow<ForecastState> = _state.asStateFlow()

    init {
        loadForecast()
    }

    private fun loadForecast() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            getForecastUseCase.execute(locationName, 3).fold(
                onSuccess = { forecast ->
                    _state.update {
                        it.copy(
                            forecast = forecast,
                            isLoading = false,
                            error = null
                        )
                    }
                },
                onFailure = { exception ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = exception.message ?: "Error al cargar el pronóstico"
                        )
                    }
                }
            )
        }
    }
}

