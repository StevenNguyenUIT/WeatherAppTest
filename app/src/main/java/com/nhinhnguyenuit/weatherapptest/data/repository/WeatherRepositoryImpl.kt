package com.nhinhnguyenuit.weatherapptest.data.repository

import com.nhinhnguyenuit.weatherapptest.data.network.WeatherApi
import com.nhinhnguyenuit.weatherapptest.data.model.WeatherResponse

class WeatherRepositoryImpl(private val api: WeatherApi) : WeatherRepository {
    override suspend fun fetchWeather(city: String): WeatherResponse {
        return api.getWeather(apiKey = "c9c1cac49aa7471c8b8150253242012", city = city)
    }
}