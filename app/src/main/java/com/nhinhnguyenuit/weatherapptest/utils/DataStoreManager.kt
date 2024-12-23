package com.nhinhnguyenuit.weatherapptest.utils

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


class DataStoreManager(private val context: Context){
    private val Context.dataStore by preferencesDataStore(name = "weather_prefs")

    companion object {
        val SELECTED_CITY = stringPreferencesKey("selected_city")
    }

    suspend fun saveCity(city: String){
        context.dataStore.edit { preferences ->
            preferences[SELECTED_CITY] = city
        }
    }

    suspend fun getCity(): String? {
        val preferences = context.dataStore.data.first()
        return preferences[SELECTED_CITY]
    }

    val selectedCity: Flow<String?> = context.dataStore.data.map {
        preferences -> preferences[SELECTED_CITY]
    }
}
