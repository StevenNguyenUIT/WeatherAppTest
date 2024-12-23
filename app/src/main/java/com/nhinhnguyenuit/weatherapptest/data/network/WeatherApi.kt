package com.nhinhnguyenuit.weatherapptest.data.network

import com.nhinhnguyenuit.weatherapptest.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("current.json")
    suspend fun getWeather(
        @Query("key") apiKey: String,
        @Query("q") city: String
    ): WeatherResponse
}