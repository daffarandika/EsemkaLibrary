package com.example.esemkalibrary.feature_borrowingdetail.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.esemkalibrary.core.data.LocalStorage
import com.example.esemkalibrary.core.model.Book
import com.example.esemkalibrary.feature_borrowingdetail.data.ApiService
import com.example.esemkalibrary.feature_borrowingdetail.data.BorrowingDetailUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class BorrowingDetailViewModel(val context: Context): ViewModel() {

    fun getUiState(token: String, borrowingId: String): Flow<BorrowingDetailUiState> {
        return ApiService().getUiState(token, borrowingId).flowOn(Dispatchers.Main)
    }

    val token = LocalStorage(context).token

}