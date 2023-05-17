package com.example.esemkalibrary.core.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalStorage(private val context: Context) {

    val token: Flow<String> = context.dataStore.data
        .map {
            it[TOKEN_KEY] ?: ""
        }
    suspend fun setToken(token: String) {
        context.dataStore.edit {
            it[TOKEN_KEY] = token
        }
    }
    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore("LocalStorage")
        val TOKEN_KEY = stringPreferencesKey("token")
    }
}