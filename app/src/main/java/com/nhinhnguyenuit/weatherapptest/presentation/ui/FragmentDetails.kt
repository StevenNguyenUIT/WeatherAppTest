package com.nhinhnguyenuit.weatherapptest.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.nhinhnguyenuit.weatherapptest.R
import com.nhinhnguyenuit.weatherapptest.domain.model.Weather
import com.nhinhnguyenuit.weatherapptest.presentation.theme.AppStyles
import com.nhinhnguyenuit.weatherapptest.presentation.theme.Dimens

@Composable
fun FragmentDetails(weather: Weather?) {
    Card(
        modifier = Modifier
            .width(Dimens.DetailCardWidth)
            .height(Dimens.DetailCardHeight),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF2F2F2)
        ),
        shape = RoundedCornerShape(Dimens.RadiusSize)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Item(
                label = stringResource(R.string.humidity_label),
                content = weather?.humidity,
                addingContent = stringResource(R.string.percent)
            )
            Item(label = stringResource(R.string.uv_label), content = weather?.uvIndex?.toInt())
            Item(
                label = stringResource(R.string.feels_like_label),
                labelStyle = AppStyles.PrimaryLightGrayText8Style,
                content = weather?.feelsLike?.toInt(),
                addingContent = stringResource(id = R.string.degree)
            )
        }
    }
}