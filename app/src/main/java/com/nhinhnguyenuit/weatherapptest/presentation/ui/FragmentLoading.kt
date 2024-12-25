package com.nhinhnguyenuit.weatherapptest.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.nhinhnguyenuit.weatherapptest.R
import com.nhinhnguyenuit.weatherapptest.presentation.theme.AppStyles
import com.nhinhnguyenuit.weatherapptest.presentation.theme.Dimens
import com.nhinhnguyenuit.weatherapptest.presentation.theme.WeatherAppTestTheme

@Composable
fun FragmentLoading(modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.no_city_selected),
            style = AppStyles.PrimaryText30Style,
            modifier = Modifier.padding(Dimens.MediumPadding)
        )
        Text(
            text = stringResource(R.string.please_search_for_a_city),
            style = AppStyles.PrimaryTextStyle
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNotify() {
    WeatherAppTestTheme {
        FragmentLoading(modifier = Modifier.padding(Dimens.LargePadding))
    }
}

