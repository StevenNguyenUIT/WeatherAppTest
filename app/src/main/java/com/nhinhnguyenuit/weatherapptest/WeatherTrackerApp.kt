package com.nhinhnguyenuit.weatherapptest

import android.app.Application
import com.nhinhnguyenuit.weatherapptest.di.dataStoreModule
import com.nhinhnguyenuit.weatherapptest.di.netWorkModule
import com.nhinhnguyenuit.weatherapptest.di.repositoryModule
import com.nhinhnguyenuit.weatherapptest.di.useCaseModule
import com.nhinhnguyenuit.weatherapptest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherTrackerApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@WeatherTrackerApp)
            modules(listOf(netWorkModule, repositoryModule, useCaseModule, dataStoreModule, viewModelModule))
        }
    }
}