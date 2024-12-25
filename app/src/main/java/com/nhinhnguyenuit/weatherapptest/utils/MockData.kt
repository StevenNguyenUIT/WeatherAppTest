package com.nhinhnguyenuit.weatherapptest.utils

import com.nhinhnguyenuit.weatherapptest.domain.model.Weather

object MockData {
    val weather = Weather(
        city = "Hyderabad", temperature = 30.4, condition = "cloudy",
        icon = "https://cdn.weatherapi.com/weather/64x64/day/113.png",
        humidity = 4,
        uvIndex = 4.2,
        feelsLike = 3.3
    )
}