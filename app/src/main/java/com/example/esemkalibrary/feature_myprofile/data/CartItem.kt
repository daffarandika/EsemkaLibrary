package com.example.esemkalibrary.feature_myprofile.data

import java.time.LocalDate

data class CartItem(
    val id: String,
    val start: LocalDate = LocalDate.now(),
    val end: LocalDate = LocalDate.now(),
    val returnedAt: LocalDate = LocalDate.now(),
    val bookCount: Int = 0,
    val status: String = "Borrowing"
)
