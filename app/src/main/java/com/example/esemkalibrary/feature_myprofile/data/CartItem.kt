package com.example.esemkalibrary.feature_myprofile.data

import java.time.LocalDateTime

data class CartItem(
    val id: String,
    val start: LocalDateTime = LocalDateTime.now(),
    val end: LocalDateTime = LocalDateTime.now(),
    val returnedAt: LocalDateTime = LocalDateTime.now(),
    val bookCount: Int = 0,
    val status: String = "Borrowing"
)
