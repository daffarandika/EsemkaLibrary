package com.example.esemkalibrary.feature_home.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.esemkalibrary.core.data.LocalStorage
import com.example.esemkalibrary.core.model.BookHeader
import com.example.esemkalibrary.feature_home.data.ApiService
import com.example.esemkalibrary.feature_home.data.HomeUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class UiState(val isLoading: Boolean, val data: List<BookHeader>? = null, val error: String? = null)

sealed class Result<out T> {
    object Loading : Result<Nothing>()
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
}

class HomeViewModel(val context: Context): ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _homeState = MutableStateFlow(UiState(isLoading = true))
    val homeState: StateFlow<UiState> = _homeState.asStateFlow()

    private val apiService = ApiService(context)

    val token = LocalStorage(context).token
    lateinit var newToken:String

    init {
        viewModelScope.launch{
            searchBooks(query = "")
            newToken = token.filter{
                it != ""
            }.first()
        }
    }

    fun searchBooks(query: String) {
        viewModelScope.launch {
            _homeState.update { state ->
                state.copy(isLoading = true)
            }
            apiService.searchBooks(newToken, query).catch { err ->
                _homeState.update { state ->
                    state.copy(error = err.message, isLoading = false)
                }
            }.collect{ books ->
                _homeState.update { state ->
                    state.copy(data = books, isLoading = false)
                }
            }
        }
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