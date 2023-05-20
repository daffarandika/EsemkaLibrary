package com.example.esemkalibrary.feature_myprofile.data

import java.time.LocalDate

data class BorrowDetail(
    val startDate: LocalDate,
    val endDate: LocalDate,
    val qty: Int,
    val status: String
)