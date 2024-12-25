package com.nhinhnguyenuit.weatherapptest.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.nhinhnguyenuit.weatherapptest.presentation.theme.AppStyles
import com.nhinhnguyenuit.weatherapptest.presentation.theme.Dimens

@Composable
fun Item(
    label: String,
    labelStyle: TextStyle = AppStyles.PrimaryLightGrayText12Style,
    content: Int?,
    contentStyle: TextStyle = AppStyles.PrimaryDarkGrayTextStyle,
    addingContent: String = ""
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = Dimens.SmallPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = label,
            style = labelStyle
        )
        Text(
            text = "$content$addingContent",
            style = contentStyle
        )
    }
}