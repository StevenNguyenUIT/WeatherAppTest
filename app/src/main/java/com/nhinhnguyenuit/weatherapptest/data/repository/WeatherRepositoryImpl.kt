package com.nhinhnguyenuit.weatherapptest.data.repository

import com.nhinhnguyenuit.weatherapptest.BuildConfig
import com.nhinhnguyenuit.weatherapptest.data.network.WeatherApi
import com.nhinhnguyenuit.weatherapptest.data.model.WeatherResponse

class WeatherRepositoryImpl(private val api: WeatherApi) : WeatherRepository {
    override suspend fun fetchWeather(city: String): WeatherResponse {
        return api.getWeather(apiKey = BuildConfig.API_KEY, city = city)
    }
}