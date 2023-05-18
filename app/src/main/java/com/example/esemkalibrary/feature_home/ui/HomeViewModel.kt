package com.example.esemkalibrary.feature_home.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.esemkalibrary.core.data.LocalStorage
import com.example.esemkalibrary.core.model.BookHeader
import com.example.esemkalibrary.feature_home.data.ApiService
import com.example.esemkalibrary.feature_home.data.HomeUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*


class HomeViewModel(context: Context): ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    private val apiService = ApiService(context)

    val token = LocalStorage(context).token

    fun searchBooks(token: String, query: String): Flow<List<BookHeader>> {
        return apiService.searchBooks(token, query).flowOn(Dispatchers.Main)
    }

    fun updateBooks(books: List<BookHeader>) {
        _uiState.value = uiState.value.copy(
            books = books
        )
    }

    fun updateSearchText(query: String) {
        _uiState.update {
            it.copy(searchText = query)
        }
    }

}