package com.nhinhnguyenuit.weatherapptest.data.model

data class WeatherResponse(
    val location: Location,
    val current: Current
)

data class Location(
    val name: String
)

data class Current(
    val temp_c: Double,
    val condition: Condition,
    val humidity: Int,
    val uv: Double,
    val feelslike_c: Double
)

data class Condition(
    val text: String,
    val icon: String
)