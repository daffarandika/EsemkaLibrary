package com.example.esemkalibrary.core.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class LocalStorage(private val context: Context) {

    val token2: String = runBlocking {
        context.dataStore.data.first().get(TOKEN_KEY) ?: ""
    }
    val token = context.dataStore.data.map {
        it[TOKEN_KEY] ?: ""
    }
    suspend fun clearToken() {
        setToken("")
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
    suspend fun removeItemFromCart(bookId: String) {
        context.dataStore.edit {
            if(it[CART_ITEMS_KEY]!!.contains(bookId)) {
                Log.e(":", "removeItemFromCart: ${it[CART_ITEMS_KEY]!!.split(";").filter { curr ->
                    curr != bookId
                }}", )
                it[CART_ITEMS_KEY] = it[CART_ITEMS_KEY]!!.split(";").filter { curr ->
                    curr != bookId
                }.joinToString(";")
                Log.e("TAG", "removeItemFromCart: $bookId now list looks like this ${it[CART_ITEMS_KEY]}", )
            }
        }
    }
    suspend fun addItemToCart(bookId: String) {
        if (bookId.isEmpty()) return
        context.dataStore.edit {
            if (it[CART_ITEMS_KEY] == "") {
                it[CART_ITEMS_KEY] = bookId
            } else {
                val bookIds = (it[CART_ITEMS_KEY] ?: "").split(";").toMutableSet()
                bookIds.add(bookId)
                it[CART_ITEMS_KEY] = bookIds.joinToString(";")
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