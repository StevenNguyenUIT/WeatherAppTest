package com.nhinhnguyenuit.weatherapptest.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil3.compose.AsyncImage
import com.nhinhnguyenuit.weatherapptest.R
import com.nhinhnguyenuit.weatherapptest.domain.model.Weather
import com.nhinhnguyenuit.weatherapptest.presentation.theme.AppStyles
import com.nhinhnguyenuit.weatherapptest.presentation.theme.Dimens
import com.nhinhnguyenuit.weatherapptest.presentation.viewmodel.WeatherState

@Composable
fun FragmentBasic(weather: Weather?) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = "https:${weather?.icon}",
            contentDescription = stringResource(R.string.describe_image),
            modifier = Modifier.size(Dimens.ImageSize)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(Dimens.MediumPadding)
        ) {
            Text(
                text = "${weather?.city}",
                style = AppStyles.PrimaryText30Style
            )
            Spacer(modifier = Modifier.width(Dimens.SmallPadding))
            Icon(
                painter = painterResource(id = R.drawable.arrowcustom),
                contentDescription = stringResource(
                    R.string.describe_arrow_after_city
                ),
                modifier = Modifier.size(Dimens.IconSize)
            )
        }
        Row {
            Text(
                text = "${weather?.temperature?.toInt()}",
                style = AppStyles.PrimaryText70Style
            )
            Text(
                text = stringResource(R.string.degree),
                style = AppStyles.PrimaryText30Style
            )
        }
    }
}