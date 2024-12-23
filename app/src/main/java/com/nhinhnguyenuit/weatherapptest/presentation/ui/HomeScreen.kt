package com.nhinhnguyenuit.weatherapptest.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.nhinhnguyenuit.weatherapptest.presentation.viewmodel.WeatherState
import com.nhinhnguyenuit.weatherapptest.presentation.viewmodel.WeatherViewModel

@Composable
fun HomeScreen(viewModel: WeatherViewModel) {
    val weather by viewModel.weatherState.collectAsState()
    val savedCity by viewModel.getSavedCity().collectAsState(initial = null)
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    var isSearching by remember { mutableStateOf(false) }

    // Display saved city or prompt user to search
    val currentCity = savedCity ?: "No city saved"

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Weather Tracker",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )

        // Search Bar
        SearchBar(
            searchText = searchText,
            onSearchTextChange = {
                searchText = it
            },
            onSearchSubmit = {
                // Trigger search function in ViewModel
                viewModel.loadWeather(searchText.text)
                isSearching = true
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // If weather is not loaded, show a loading message or city not found
        if (weather == null) {
            Column {
                Text(text = "No City Selected", style = MaterialTheme.typography.titleLarge)
                Text(text = "Please search for a city", style = MaterialTheme.typography.bodySmall)
            }
        } else {
            // Display the weather details for the selected city
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = currentCity, style = MaterialTheme.typography.titleSmall)
                Text(text = "Temperature: ${weather?.temperature}°C", style = MaterialTheme.typography.bodySmall)
                Text(text = "Humidity: ${weather?.humidity}%", style = MaterialTheme.typography.bodyLarge)
                Text(text = "UV Index: ${weather?.uvIndex}", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Feels Like: ${weather?.feelsLike}°C", style = MaterialTheme.typography.bodyMedium)
            }
        }

        // If the search bar is used and weather is not found, show error message
        if (isSearching && weather == null) {
            Text(
                text = "City not found, try again!",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Red,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
