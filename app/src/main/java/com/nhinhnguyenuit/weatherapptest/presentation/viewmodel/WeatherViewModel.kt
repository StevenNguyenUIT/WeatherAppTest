package com.nhinhnguyenuit.weatherapptest.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhinhnguyenuit.weatherapptest.R
import com.nhinhnguyenuit.weatherapptest.domain.model.Weather
import com.nhinhnguyenuit.weatherapptest.domain.usecase.GetWeatherUseCase
import com.nhinhnguyenuit.weatherapptest.utils.DataStoreManager
import com.nhinhnguyenuit.weatherapptest.utils.NetworkUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class WeatherState {
    data object Loading : WeatherState()
    data class Success(val weather: Weather?) : WeatherState()
    data class Error(val message: String) : WeatherState()
}

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val dataStoreManager: DataStoreManager,
    private val context: Context
) : ViewModel() {
    private val _weatherState = MutableStateFlow<WeatherState?>(WeatherState.Loading)
    val weatherState: StateFlow<WeatherState?> = _weatherState

    private val _cityState = MutableStateFlow<Boolean>(false)
    val cityState: StateFlow<Boolean> = _cityState

    init {
        // Fetch city from DataStore on initialization
        viewModelScope.launch {
            val savedCity = dataStoreManager.getCity()
            if (savedCity != null) {
                _cityState.value = true
                loadWeather(savedCity)
            } else {
                _cityState.value = false
                _weatherState.value = WeatherState.Success(null)
            }
        }
    }

    fun loadWeather(city: String) {
        viewModelScope.launch {
            if (!NetworkUtils.isInternetAvailable(context)) {
                _weatherState.value = WeatherState.Error(context.getString(R.string.no_internet_connection))
                return@launch
            }
            try {
                _weatherState.value = WeatherState.Loading
                Log.d("WeatherViewModel", "Fetching weather for: $city")
                val weatherData = getWeatherUseCase(city)
                _weatherState.value = WeatherState.Success(weatherData)
                dataStoreManager.saveCity(city)
                Log.d("WeatherViewModel", "Weather data loaded successfully")
            } catch (e: Exception) {
                Log.e("WeatherViewModel", "Error fetching weather: ${e.message}")
//                _weatherState.value = WeatherState.Error("Error fetching weather: ${e.message}")
            }
        }
    }
}