package com.example.esemkalibrary.feature_borrowingdetail.data

import com.example.esemkalibrary.core.model.Book
import java.time.LocalDate

data class BorrowingDetailUiState(
    val start: LocalDate = LocalDate.now(),
    val end: LocalDate = LocalDate.now(),
    val status: String = "",
    val books: List<Book> = listOf(),
)
