package com.nhinhnguyenuit.weatherapptest.di

import com.nhinhnguyenuit.weatherapptest.data.network.WeatherApi
import com.nhinhnguyenuit.weatherapptest.data.repository.WeatherRepository
import com.nhinhnguyenuit.weatherapptest.data.repository.WeatherRepositoryImpl
import com.nhinhnguyenuit.weatherapptest.domain.usecase.GetWeatherUseCase
import com.nhinhnguyenuit.weatherapptest.presentation.viewmodel.WeatherViewModel
import com.nhinhnguyenuit.weatherapptest.utils.DataStoreManager
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val netWorkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(WeatherApi::class.java) }
}

val repositoryModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
}

val useCaseModule = module {
    factory { GetWeatherUseCase(get()) }
}

val dataStoreModule = module {
    single { DataStoreManager(androidContext()) }
}

val viewModelModule = module {
    viewModel {
        WeatherViewModel(get(), get())
    }
}
