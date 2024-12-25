package com.nhinhnguyenuit.weatherapptest.domain.usecase

import com.nhinhnguyenuit.weatherapptest.data.repository.WeatherRepository
import com.nhinhnguyenuit.weatherapptest.domain.model.Weather

class GetWeatherUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(city: String): Weather{
        val response = repository.fetchWeather(city = city)
        return Weather(
            city = response.location.name,
            temperature =  response.current.temp_c,
            condition = response.current.condition.text,
            icon = response.current.condition.icon,
            humidity = response.current.humidity,
            uvIndex = response.current.uv,
            feelsLike = response.current.feelslike_c
        )
    }
}