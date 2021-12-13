package com.ecommercemvvmpractice.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import javax.inject.Inject


private val Context.dataStore: DataStore<Preferences> by
preferencesDataStore(name = "user_preferences")

class UserPreferences @Inject constructor(private val context: Context) {

    suspend fun saveUserData(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN] = token
        }

    }

    companion object {
        val TOKEN = stringPreferencesKey("token")
    }

}