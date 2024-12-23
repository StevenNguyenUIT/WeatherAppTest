package com.nhinhnguyenuit.weatherapptest.data.repository

import com.nhinhnguyenuit.weatherapptest.data.model.WeatherResponse

interface WeatherRepository {
    suspend fun fetchWeather(city: String): WeatherResponse
}