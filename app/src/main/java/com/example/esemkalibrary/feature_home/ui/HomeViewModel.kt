package com.example.esemkalibrary.feature_home.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.esemkalibrary.core.data.ApiConfig
import com.example.esemkalibrary.core.data.LocalStorage
import com.example.esemkalibrary.core.model.BookHeader
import com.example.esemkalibrary.feature_home.data.ApiService
import com.example.esemkalibrary.feature_home.data.HomeUiState
import com.example.esemkalibrary.feature_login.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL


class HomeViewModel(context: Context): ViewModel() {
    private val _uiState: MutableStateFlow<Result<List<BookHeader>>> = MutableStateFlow(Result.Loading)
    val uiState: StateFlow<Result<List<BookHeader>>> = _uiState.asStateFlow()
    private val apiService = ApiService(context)

    val token = LocalStorage(context).token

    fun searchBooks(token: String, query: String?) {
        _uiState.value = Result.Loading
        try {
            viewModelScope.launch(Dispatchers.IO) {
                if (token.isBlank()) {
                    return@launch
                }
                val conn =
                    URL(if (query.isNullOrEmpty()) "${ApiConfig.BASE_URL}:${ApiConfig.PORT}/Api/Book" else "${ApiConfig.BASE_URL}:${ApiConfig.PORT}/Api/Book?searchText=$query").openConnection() as HttpURLConnection
                conn.requestMethod = "GET"
                conn.setRequestProperty("Authorization", "Bearer $token")
                conn.setRequestProperty("Content-Type", "application/json")
                conn.setRequestProperty("Accept", "application/json")

                val inputString = conn.inputStream.bufferedReader().readText()
                val jsonArray = JSONArray(inputString)
                val books = mutableListOf<BookHeader>()
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    books.add(BookHeader(
                        id = jsonObject.getString("id"),
                        name = jsonObject.getString("name"),
                        authors = jsonObject.getString("authors"),
                        image = apiService.getImage(token = token, id = jsonObject.getString("id"))
                    ))
                }
                _uiState.value = Result.Success(books)
            }
        } catch (e: java.lang.Exception) {
            _uiState.value = Result.Error(e.message.toString())
        }
    }

//    fun updateBooks(books: List<BookHeader>) {
//        _uiState.update {
//            it.copy(books = books)
//        }
//    }
//
//    fun updateSearchText(query: String) {
//        _uiState.update {
//            it.copy(searchText = query)
//        }
//    }

}