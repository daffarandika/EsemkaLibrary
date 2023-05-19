package com.example.esemkalibrary.feature_mycart.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.esemkalibrary.core.data.LocalStorage
import com.example.esemkalibrary.core.model.Book
import com.example.esemkalibrary.feature_mycart.data.ApiService
import com.example.esemkalibrary.feature_mycart.data.MyCartUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate

class MyCartViewModel(val context: Context): ViewModel() {

    private val _uiState = MutableStateFlow(MyCartUiState())
    val uiState: StateFlow<MyCartUiState> = _uiState.asStateFlow()

    fun updateCartItems(cartItems: List<Book>) {
        _uiState.update {
            it.copy(cartItems = cartItems)
        }
    }

    val bookIdsInCart = LocalStorage(context).bookIdInCart
    val token = LocalStorage(context).token

    fun getBooksFromIds(bookIds: List<String>, token: String): Flow<List<Book>> {
        return ApiService().getBooksFromId(token, bookIds).flowOn(Dispatchers.Main)
    }

    fun removeItemFromCart(bookId: String){
        viewModelScope.launch {
            LocalStorage(context).removeItemFromCart(bookId)
        }
    }

    fun updateStartDate(startDate: LocalDate) {
        _uiState.update {
            it.copy(startDate = startDate, endDate = startDate.plusDays(3))
        }
    }

    fun makeStartDateDialogVisible() {
        _uiState.update {
            it.copy(showStartDateDialog = true)
        }
    }
    fun makeStartDateDialogInvisible() {
        _uiState.update {
            it.copy(showStartDateDialog = false)
        }
    }

    fun uploadCart(bookIds: List<String>, token: String, start: LocalDate, end: LocalDate) {
        viewModelScope.launch {
            ApiService().postCartToApi(bookIds, token, start, end)
            LocalStorage(context).clearCartItems()
        }
    }
}