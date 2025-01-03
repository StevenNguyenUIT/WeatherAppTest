package com.nhinhnguyenuit.weatherapptest.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.nhinhnguyenuit.weatherapptest.R
import com.nhinhnguyenuit.weatherapptest.domain.model.Weather
import com.nhinhnguyenuit.weatherapptest.presentation.theme.AppStyles
import com.nhinhnguyenuit.weatherapptest.presentation.theme.Dimens
import com.nhinhnguyenuit.weatherapptest.presentation.theme.GraySurface
import com.nhinhnguyenuit.weatherapptest.presentation.theme.WeatherAppTestTheme
import com.nhinhnguyenuit.weatherapptest.utils.MockData

@Composable
fun FragmentSearchResult(weather: Weather?) {
    Card(
        modifier = Modifier
            .height(Dimens.ResultCardHeight)
            .fillMaxWidth(Dimens.SearchResultWidth),
        colors = CardDefaults.cardColors(
            containerColor = GraySurface
        ),
        shape = RoundedCornerShape(Dimens.RadiusSize)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxHeight().padding(Dimens.SmallPadding),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //city
                Text(
                    text = "${weather?.city}",
                    style = AppStyles.PrimaryText20Style
                )
                //degree
                Row {
                    Text(
                        text = "${weather?.temperature?.toInt()}",
                        style = AppStyles.PrimaryText60Style
                    )
                    Text(
                        text = stringResource(id = R.string.degree),
                        style = AppStyles.PrimaryText30Style
                    )
                }
            }
            //image
            AsyncImage(
                model = "https:${weather?.icon}",
                contentDescription = stringResource(id = R.string.describe_image),
                modifier = Modifier.size(Dimens.ImageSize)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearching() {
    WeatherAppTestTheme {
        FragmentSearchResult(
            weather = MockData.weather
        )
    }
}