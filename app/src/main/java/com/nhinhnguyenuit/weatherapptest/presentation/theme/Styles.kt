package com.nhinhnguyenuit.weatherapptest.presentation.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

object AppStyles {
    val PrimaryTextStyle = TextStyle(
        fontSize = 15.sp,
        color = BlackTextBold
    )
    val PrimaryDarkGrayTextStyle = PrimaryTextStyle.copy(
        color = GrayTextBold
    )
    val PrimaryLightGrayTextStyle = PrimaryTextStyle.copy(
        color = GrayText
    )
    val PrimaryLightGrayText12Style = PrimaryLightGrayTextStyle.copy(
        fontSize = 12.sp
    )
    val PrimaryLightGrayText8Style = PrimaryLightGrayTextStyle.copy(
        fontSize = 8.sp
    )
    val PrimaryText20Style = PrimaryTextStyle.copy(
        fontSize = 20.sp
    )
    val PrimaryText30Style = PrimaryTextStyle.copy(
        fontSize = 30.sp
    )
    val PrimaryText60Style = PrimaryTextStyle.copy(
        fontSize = 60.sp
    )
    val PrimaryText70Style = PrimaryTextStyle.copy(
        fontSize = 70.sp
    )
}