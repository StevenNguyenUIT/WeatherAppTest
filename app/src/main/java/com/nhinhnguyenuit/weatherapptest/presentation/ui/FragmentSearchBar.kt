package com.nhinhnguyenuit.weatherapptest.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import com.nhinhnguyenuit.weatherapptest.R
import com.nhinhnguyenuit.weatherapptest.presentation.theme.AppStyles
import com.nhinhnguyenuit.weatherapptest.presentation.theme.Dimens
import com.nhinhnguyenuit.weatherapptest.presentation.theme.GraySurface
import com.nhinhnguyenuit.weatherapptest.presentation.theme.GrayText

@Composable
fun FragmentSearchBar(
    searchText: TextFieldValue,
    onSearchTextChange: (TextFieldValue) -> Unit,
    onSearchSubmit: () -> Unit
) {
    OutlinedTextField(
        value = searchText,
        onValueChange = onSearchTextChange,
        placeholder = {
            Text(
                text = stringResource(R.string.search_location),
                style = AppStyles.PrimaryLightGrayTextStyle
            )
        },
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            onSearchSubmit()
        }),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            unfocusedContainerColor = GraySurface,
            focusedContainerColor = GraySurface
        ),
        modifier = Modifier
            .fillMaxWidth(Dimens.SearchBarWidth)
            .padding(top = Dimens.XLargePadding),
        trailingIcon = {
            IconButton(onClick = {
                onSearchSubmit()
            }) {
                Icon(
                    imageVector = Icons.Default.Search, contentDescription = stringResource(R.string.search_describe),
                    tint = GrayText
                )
            }
        },
        shape = RoundedCornerShape(Dimens.RadiusSize)
    )
}

