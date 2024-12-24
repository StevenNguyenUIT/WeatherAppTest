package com.nhinhnguyenuit.weatherapptest.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.nhinhnguyenuit.weatherapptest.R
import com.nhinhnguyenuit.weatherapptest.domain.model.Weather
import com.nhinhnguyenuit.weatherapptest.presentation.viewmodel.WeatherViewModel

@Composable
fun HomeScreen(viewModel: WeatherViewModel) {
    val weather by viewModel.weatherState.collectAsState()
//    val savedCity by viewModel.getSavedCity().collectAsState(initial = null)
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    var isSearching by remember { mutableStateOf(false) }

//    // Display saved city or prompt user to search
//    val currentCity = savedCity ?: "No city saved"

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
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
        // If weather is not loaded, show a loading message
        if (!isSearching && weather == null) {
            LoadingScreen(modifier = Modifier.weight(1f))
        } else if (!isSearching) {
            // Display the weather details for the selected city
            FragmentLoadingBasicInfo(weather)
            FragmentLoadingMoreInfo(weather)
        } else if (weather != null) {
            // if can search city, show a basic information
            Spacer(modifier = Modifier.height(30.dp))
            FragmentSearchingBasicInfo(weather)
        } else {
            Text(text = "Not found")
        }
    }
}

@Composable
private fun FragmentSearchingBasicInfo(weather: Weather?) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(117.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF2F2F2)
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            Modifier.width(336.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //city
                Text(
                    text = "${weather?.city}",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                        )
                )
                //degree
                Text(
                    text = "${weather?.temperature?.toInt()}\u00B0", style = TextStyle(
                        fontSize = 60.sp
                    )
                )
            }
            //image
            AsyncImage(
                model = "https:${weather?.icon}", contentDescription = "image",
                modifier = Modifier.size(123.dp)
            )
        }

    }
}

@Composable
private fun FragmentLoadingMoreInfo(weather: Weather?) {
    Card(
        modifier = Modifier
            .width(274.dp)
            .height(75.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF2F2F2)
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Humidity", style = TextStyle(fontSize = 12.sp))
                Text(
                    text = "${weather?.humidity}%",
                    style = TextStyle(fontSize = 15.sp)
                )
            }
            Column {
                Text(text = "UV", style = TextStyle(fontSize = 12.sp))
                Text(
                    text = "${weather?.uvIndex?.toInt()}",
                    style = TextStyle(fontSize = 15.sp)
                )
            }
            Column {
                Text(text = "Feels Like", style = TextStyle(fontSize = 8.sp))
                Text(
                    text = "${weather?.feelsLike?.toInt()}\u00B0",
                    style = TextStyle(fontSize = 15.sp)
                )
            }
        }
    }
}

@Composable
private fun FragmentLoadingBasicInfo(weather: Weather?) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //image
        AsyncImage(
            model = "https:${weather?.icon}", contentDescription = "image",
            modifier = Modifier.size(123.dp)
        )
        //city
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${weather?.city}", style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.width(10.dp))
            Icon(painter = painterResource(id = R.drawable.arrowcustom), contentDescription = "png",
                modifier = Modifier.size(21.dp))
        }
        //degree
        Text(
            text = "${weather?.temperature?.toInt()}\u00B0", style = TextStyle(
                fontSize = 70.sp
            )
        )
    }
}

@Composable
private fun LoadingScreen(modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.no_city_selected),
            fontSize = 30.sp,
        )
        Text(
            text = stringResource(R.string.please_search_for_a_city),
            fontSize = 15.sp,
        )
    }
}
