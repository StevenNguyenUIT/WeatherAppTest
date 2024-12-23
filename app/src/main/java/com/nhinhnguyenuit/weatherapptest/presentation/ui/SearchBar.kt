package com.nhinhnguyenuit.weatherapptest.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun SearchBar(
    searchText: TextFieldValue,
    onSearchTextChange: (TextFieldValue) -> Unit,
    onSearchSubmit: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.Center) {
        BasicTextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            textStyle = MaterialTheme.typography.bodyMedium,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                onSearchSubmit()
            }),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(8.dp)
                .background(Color.Gray.copy(alpha = 0.1f), shape = MaterialTheme.shapes.small)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = { onSearchSubmit() },
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Text("Search")
        }
    }
}