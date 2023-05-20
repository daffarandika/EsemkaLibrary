package com.example.esemkalibrary.feature_bookdetail.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.esemkalibrary.core.data.LocalStorage
import com.example.esemkalibrary.feature_bookdetail.data.ApiService
import com.example.esemkalibrary.feature_bookdetail.data.BookDetailUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class BookDetailViewModel(val context: Context): ViewModel() {
    private val apiService = ApiService()

    fun getData(token: String, bookId: String): Flow<BookDetailUiState> = apiService.getData(token = token, bookId = bookId)

    fun addToCart(bookId: String) {
        viewModelScope.launch {
            LocalStorage(context).addItemToCart(bookId)
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            LocalStorage(context).clearCartItems()
        }
    }

    val token = LocalStorage(context).token
}