package com.nhinhnguyenuit.weatherapptest.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhinhnguyenuit.weatherapptest.utils.DataStoreManager
import com.nhinhnguyenuit.weatherapptest.data.model.WeatherResponse
import com.nhinhnguyenuit.weatherapptest.domain.model.Weather
import com.nhinhnguyenuit.weatherapptest.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlin.contracts.contract

sealed class WeatherState {
    object Loading : WeatherState()
    data class Success(val weather: Weather) : WeatherState()
    data class Error(val message: String) : WeatherState()
}

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val dataStoreManager: DataStoreManager,
): ViewModel() {
    private val _weatherState = MutableStateFlow<Weather?>(null)
    val weatherState: StateFlow<Weather?> = _weatherState

    private val _cityState = MutableStateFlow<String?>(null)
    val cityState: StateFlow<String?> = _cityState

    init {
        // Fetch city from DataStore on initialization
        viewModelScope.launch {
            val savedCity = dataStoreManager.getCity()
            if (savedCity != null) {
                _cityState.value = savedCity
                loadWeather(savedCity)
            } else {
//                _weatherState.value = WeatherState.Error("City not found. Try again.")
            }
        }
    }

    fun loadWeather(city: String) {
        viewModelScope.launch {
            try {
                // Log the request to check if it's being called
                Log.d("WeatherViewModel", "Fetching weather for: $city")
                val weatherData = getWeatherUseCase(city)
                if (weatherData != null) {
                    _weatherState.value = weatherData
                    dataStoreManager.saveCity(city)
                    Log.d("WeatherViewModel", "Weather data loaded successfully")
                } else {
                    Log.e("WeatherViewModel", "Weather data not found")
                    _weatherState.value = null
                }
            } catch (e: Exception) {
                Log.e("WeatherViewModel", "Error fetching weather: ${e.message}")
                _weatherState.value = null
            }

        }
    }

    fun getSavedCity() = dataStoreManager.selectedCity
}