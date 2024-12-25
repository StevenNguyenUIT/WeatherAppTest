package com.nhinhnguyenuit.weatherapptest.presentation.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object AppStyles {
    val PrimaryTextStyle = TextStyle(
        fontSize = 15.sp,
        color = Color(0xFF2C2C2C)
    )
    val PrimaryTextBoldStyle = PrimaryTextStyle.copy(
        fontWeight = FontWeight.Bold
    )
    val PrimaryDarkGrayTextStyle = PrimaryTextStyle.copy(
        color = Color(0xFF9A9A9A)
    )
    val PrimaryLightGrayTextStyle = PrimaryTextStyle.copy(
        color = Color(0xFFC4C4C4)
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
    val PrimaryText20BoldStyle = PrimaryText20Style.copy(
        fontWeight = FontWeight.Bold
    )
    val PrimaryText30Style = PrimaryTextStyle.copy(
        fontSize = 30.sp
    )
    val PrimaryText30BoldStyle = PrimaryText30Style.copy(
        fontWeight = FontWeight.Bold
    )
    val PrimaryText60Style = PrimaryTextStyle.copy(
        fontSize = 60.sp
    )
    val PrimaryText60BoldStyle = PrimaryText60Style.copy(
        fontWeight = FontWeight.Bold
    )
    val PrimaryText70Style = PrimaryTextStyle.copy(
        fontSize = 70.sp
    )
    val PrimaryText70BoldStyle = PrimaryText70Style.copy(
        fontWeight = FontWeight.Bold
    )
}