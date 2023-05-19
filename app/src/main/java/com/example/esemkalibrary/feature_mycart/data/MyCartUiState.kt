package com.example.esemkalibrary.feature_mycart.data

import com.example.esemkalibrary.core.model.Book
import java.time.LocalDate

data class MyCartUiState (
    val cartItems: List<Book> = emptyList(),
    val showStartDateDialog: Boolean = false,
    val startDate: LocalDate = LocalDate.now(),
    val endDate: LocalDate = LocalDate.now()
)
