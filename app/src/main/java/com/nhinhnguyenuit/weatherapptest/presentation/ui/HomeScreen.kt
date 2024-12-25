package com.nhinhnguyenuit.weatherapptest.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.nhinhnguyenuit.weatherapptest.domain.model.Weather
import com.nhinhnguyenuit.weatherapptest.presentation.theme.Dimens
import com.nhinhnguyenuit.weatherapptest.presentation.theme.WeatherAppTestTheme
import com.nhinhnguyenuit.weatherapptest.presentation.viewmodel.WeatherState
import com.nhinhnguyenuit.weatherapptest.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(viewModel: WeatherViewModel = koinViewModel<WeatherViewModel>()) {
    val weatherState = viewModel.weatherState.collectAsState()
    val cityState = viewModel.cityState.collectAsState()
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    var isSearching by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Search Bar
        FragmentSearchBar(
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
        when (val state = weatherState.value) {
            is WeatherState.Loading -> Text(text = "")
            is WeatherState.Success -> {
                if (!isSearching && !cityState.value) {
                    // If weather is not loaded, show a loading message
                    FragmentLoading(modifier = Modifier.weight(1f))
                } else if (!isSearching) {
                    // Display the weather details for the selected city
                    Spacer(modifier = Modifier.height(Dimens.LargePadding))
                    FragmentBasic(state.weather)
                    Spacer(modifier = Modifier.height(Dimens.LargePadding))
                    FragmentDetails(state.weather)
                } else {
                    // if can search city, show a basic information
                    Spacer(modifier = Modifier.height(Dimens.LargePadding))
                    FragmentSearchResult(state.weather)
                }
            }
            is WeatherState.Error -> Text("Error: ${state.message}")
            else -> Text("")
        }
    }
}

//Preview for all HomeScreen
val weather = Weather(
    city = "Hyderabad", temperature = 30.4, condition = "cloudy",
    icon = "https://cdn.weatherapi.com/weather/64x64/day/113.png",
    humidity = 4,
    uvIndex = 4.2,
    feelsLike = 3.3
)

@Preview(showBackground = true)
@Composable
fun PreviewNotify() {
    WeatherAppTestTheme {
        FragmentLoading(modifier = Modifier.padding(Dimens.LargePadding))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoading() {
    WeatherAppTestTheme {
        FragmentBasic(
            weather = weather
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearching() {
    WeatherAppTestTheme {
        FragmentSearchResult(
            weather = weather
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoadingDetail() {
    WeatherAppTestTheme {
        FragmentDetails(
            weather = weather
        )
    }
}
