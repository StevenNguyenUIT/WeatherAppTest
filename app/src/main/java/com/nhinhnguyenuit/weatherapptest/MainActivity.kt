package com.nhinhnguyenuit.weatherapptest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nhinhnguyenuit.weatherapptest.presentation.theme.WeatherAppTestTheme
import com.nhinhnguyenuit.weatherapptest.presentation.ui.HomeScreen
import com.nhinhnguyenuit.weatherapptest.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTestTheme {
                val viewModel = koinViewModel<WeatherViewModel>()
                HomeScreen(viewModel = viewModel)
            }
        }
    }
}
