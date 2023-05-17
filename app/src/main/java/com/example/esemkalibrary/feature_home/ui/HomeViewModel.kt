package com.example.esemkalibrary.feature_home.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.esemkalibrary.core.data.LocalStorage
import com.example.esemkalibrary.core.data.LocalStorage.Companion.dataStore
import com.example.esemkalibrary.core.model.BookHeader
import com.example.esemkalibrary.feature_home.data.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.json.JSONArray
import kotlin.math.log


class HomeViewModel(context: Context): ViewModel() {
    private val apiService = ApiService(context)

    val token = LocalStorage(context).token

    fun getBooks(token: String): Flow<List<BookHeader>> {
        return apiService.getBooks(token).flowOn(Dispatchers.Main)
    }

}