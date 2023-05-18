package com.example.esemkalibrary.feature_bookdetail.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.esemkalibrary.core.data.LocalStorage
import com.example.esemkalibrary.feature_bookdetail.data.ApiService
import com.example.esemkalibrary.feature_bookdetail.data.BookDetailUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BookDetailViewModel(val context: Context): ViewModel() {
    private val apiService = ApiService(context)

    fun getData(token: String, bookId: String): Flow<BookDetailUiState> = apiService.getData(token = token, bookId = bookId)

    val token = LocalStorage(context).token
}