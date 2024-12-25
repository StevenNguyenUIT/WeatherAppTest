package com.nhinhnguyenuit.weatherapptest.domain.model

data class Weather(
    val city: String,
    val temperature: Double,
    val condition: String,
    val icon: String,
    val humidity: Int,
    val uvIndex: Double,
    val feelsLike: Double
)