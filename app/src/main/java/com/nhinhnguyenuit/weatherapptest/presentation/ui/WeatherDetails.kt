package com.nhinhnguyenuit.weatherapptest.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nhinhnguyenuit.weatherapptest.data.model.WeatherResponse

@Composable
fun WeatherDetails(weather: WeatherResponse) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "City: ${weather.location.name}")
        Text(text = "Temperature: ${weather.current.temp_c}°C")
        Text(text = "Condition: ${weather.current.condition.text}")
        Text(text = "Humidity: ${weather.current.humidity}%")
        Text(text = "UV Index: ${weather.current.uv}")
        Text(text = "Feels like: ${weather.current.feelslike_c}°C")
    }
}
