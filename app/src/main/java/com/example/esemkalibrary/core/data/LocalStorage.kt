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
    val bookIdInCart: Flow<String> = context.dataStore.data
        .map {
            it[CART_ITEMS_KEY] ?: ""
        }
    suspend fun setToken(token: String) {
        context.dataStore.edit {
            it[TOKEN_KEY] = token
        }
    }
    suspend fun addItemToCart(bookId: String) {
        context.dataStore.edit {
            if (it[CART_ITEMS_KEY] == "") {
                it[CART_ITEMS_KEY] = bookId
            } else {
                if (!it[CART_ITEMS_KEY]?.split(";")?.contains(bookId)!!) {
                    it[CART_ITEMS_KEY] = "${it[CART_ITEMS_KEY]};$bookId"
                }
            }
        }
    }
    suspend fun clearCartItems(){
        context.dataStore.edit {
            it[CART_ITEMS_KEY] = ""
        }
    }
    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore("LocalStorage")
        val TOKEN_KEY = stringPreferencesKey("token")
        val CART_ITEMS_KEY = stringPreferencesKey("cart_items")
    }
}