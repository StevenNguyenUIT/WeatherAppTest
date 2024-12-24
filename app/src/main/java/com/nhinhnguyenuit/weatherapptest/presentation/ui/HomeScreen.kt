package com.nhinhnguyenuit.weatherapptest.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.nhinhnguyenuit.weatherapptest.R
import com.nhinhnguyenuit.weatherapptest.domain.model.Weather
import com.nhinhnguyenuit.weatherapptest.presentation.theme.WeatherAppTestTheme
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
            // If weather is not loaded, show a loading message
            LoadingScreen(modifier = Modifier.weight(1f))
        } else if (!isSearching) {
            // Display the weather details for the selected city
            Spacer(modifier = Modifier.height(30.dp))
            FragmentLoadingBasicInfo(weather)
            Spacer(modifier = Modifier.height(30.dp))
            FragmentLoadingMoreInfo(weather)
        } else if (weather != null) {
            // if can search city, show a basic information
            Spacer(modifier = Modifier.height(30.dp))
            FragmentSearchingBasicInfo(weather)
        } else {}
    }
}

@Composable
private fun FragmentSearchingBasicInfo(weather: Weather?) {
    Card(
        modifier = Modifier.height(117.dp).fillMaxWidth(0.9f),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF2F2F2)
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
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
                Row {
                    Text(
                        text = "${weather?.temperature?.toInt()}", style = TextStyle(
                            fontSize = 60.sp
                        )
                    )
                    Text(
                        text = "\u00B0", style = TextStyle(
                            fontSize = 30.sp
                        )
                    )
                }
            }
            //image
            AsyncImage(
                model = "https:${weather?.icon}", contentDescription = "image",
                modifier = Modifier.size(83.dp)
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
            Item(label = "Humidity", content = weather?.humidity, addingContent = "%")
            Item(label = "UV", content = weather?.uvIndex?.toInt())
            Item(label = "Feels Like", fontLabel = 8.sp, content = weather?.feelsLike?.toInt(), addingContent = "\u00B0")
        }
    }
}

@Composable
private fun Item(
    label: String,
    fontLabel: TextUnit = 12.sp,
    colorLabel: Color = Color(0xFFC4C4C4),
    content: Int?,
    fontContent: TextUnit = 15.sp,
    colorContent: Color = Color(0xFF9A9A9A),
    addingContent: String = ""
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = label,
            color = colorLabel,
            style = TextStyle(fontSize = fontLabel)
        )
        Text(
            text = "$content$addingContent",
            color = colorContent,
            style = TextStyle(fontSize = fontContent)
        )
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
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "${weather?.city}", style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                painter = painterResource(id = R.drawable.arrowcustom), contentDescription = "png",
                modifier = Modifier.size(21.dp)
            )
        }
        //degree
        Row {
            Text(
                text = "${weather?.temperature?.toInt()}", style = TextStyle(
                    fontSize = 70.sp
                )
            )
            Text(
                text = "\u00B0", style = TextStyle(
                    fontSize = 40.sp
                )
            )
        }

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
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(20.dp)
        )
        Text(
            text = stringResource(R.string.please_search_for_a_city),
            style = MaterialTheme.typography.displayMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun previewNotify() {
    WeatherAppTestTheme {
        LoadingScreen(modifier = Modifier.padding(50.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoading() {
    WeatherAppTestTheme {
        FragmentLoadingBasicInfo(
            weather = Weather(
                city = "Hyderabad", temperature = 30.4, condition = "cloundy",
                icon = "https://cdn.weatherapi.com/weather/64x64/day/113.png",
                humidity = 4,
                uvIndex = 4.2,
                feelsLike = 3.3
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearching() {
    WeatherAppTestTheme {
        FragmentSearchingBasicInfo(
            weather = Weather(
                city = "Hyderabad", temperature = 30.4, condition = "cloundy",
                icon = "https://cdn.weatherapi.com/weather/64x64/day/113.png",
                humidity = 4,
                uvIndex = 4.2,
                feelsLike = 3.3
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoadingDetail() {
    WeatherAppTestTheme {
        FragmentLoadingMoreInfo(
            weather = Weather(
                city = "Hyderabad", temperature = 30.4, condition = "cloundy",
                icon = "https://cdn.weatherapi.com/weather/64x64/day/113.png",
                humidity = 4,
                uvIndex = 4.2,
                feelsLike = 3.3
            )
        )
    }
}
