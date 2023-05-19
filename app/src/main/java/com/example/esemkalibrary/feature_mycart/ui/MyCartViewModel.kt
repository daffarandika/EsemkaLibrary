package com.example.esemkalibrary.feature_mycart.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.esemkalibrary.core.model.Book
import com.example.esemkalibrary.feature_mycart.data.MyCartUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate

class MyCartViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(MyCartUiState())
    val uiState: StateFlow<MyCartUiState> = _uiState.asStateFlow()

    fun updateCartItems(cartItems: List<Book>) {
        _uiState.update {
            it.copy(cartItems = cartItems)
        }
    }

    fun updateStartDate(date: LocalDate) {
        _uiState.update {
            it.copy(startDate = date)
        }
            Log.e("TAG", "updateStartDate: ${uiState.value.startDate}", )
    }

    fun updateEndDate(date: LocalDate) {
        _uiState.update {
            it.copy(endDate = date)
        }
            Log.e("TAG", "updateEndDate: ${uiState.value.endDate}", )
    }

    fun makeStartDateDialogVisible() {
        _uiState.update {
            it.copy(showStartDateDialog = true)
        }
    }
    fun makeEndDateDialogVisible() {
        _uiState.update {
            it.copy(showEndDateDialog = true)
        }
    }
    fun makeStartDateDialogInvisible() {
        _uiState.update {
            it.copy(showStartDateDialog = false)
        }
    }
    fun makeEndDateDialogInvisible() {
        _uiState.update {
            it.copy(showEndDateDialog = false)
        }
    }
}