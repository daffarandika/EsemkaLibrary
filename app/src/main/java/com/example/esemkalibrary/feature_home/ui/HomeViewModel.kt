package com.example.esemkalibrary.feature_home.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.esemkalibrary.core.data.BaseApiService
import com.example.esemkalibrary.core.data.LocalStorage
import com.example.esemkalibrary.core.model.BookHeader
import com.example.esemkalibrary.core.model.Output
import com.example.esemkalibrary.feature_home.data.ApiService
import com.example.esemkalibrary.feature_home.data.HomeUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.math.log

data class UiState(val isLoading: Boolean, val data: List<BookHeader>? = null, val error: String? = null)


class HomeViewModel(val context: Context): ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _outputState = MutableStateFlow<Output<List<BookHeader>>>(Output.Loading)
    val outputState: StateFlow<Output<List<BookHeader>>> = _outputState.asStateFlow()

    private val apiService = ApiService(context)

    val dataStore = LocalStorage(context)
    val token = dataStore.token
    lateinit var newToken: String

    init {
        viewModelScope.launch{
            searchBooks(query = "")
            newToken = token.filter{
                Log.e("TAG", "init hvm: $it", )
                it != ""
            }.firstOrNull() ?: ""
        }
    }

    fun searchBooks(query: String) {
        viewModelScope.launch {
            _outputState.value = Output.Loading
            val expirationString = dataStore.tokenExpirationDate.filter {
                it != ""
            }.firstOrNull() ?: ""
            val email = dataStore.email.filter { it != "" }.firstOrNull() ?: ""
            val password = dataStore.password.filter { it != "" }.firstOrNull() ?: ""
            val expirationDate = LocalDateTime.parse(expirationString)
            if (LocalDateTime.now() > expirationDate) {
                newToken = BaseApiService().getToken(email, password).filter { it != "" }.firstOrNull() ?: ""
            }

            Log.e("TAG", "searchBooks: a request was made with the token $newToken")
            apiService.searchBooks(newToken, query).onStart {
                _outputState.value = Output.Loading
            }.catch { err ->
                _outputState.value = Output.Error(err)
            }.collect{ books ->
                _outputState.value = Output.Success(books)
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