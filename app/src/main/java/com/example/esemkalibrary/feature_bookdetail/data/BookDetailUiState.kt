package com.example.esemkalibrary.feature_bookdetail.data

import androidx.compose.ui.graphics.ImageBitmap

data class BookDetailUiState(
    val id: String = "",
    val name: String="",
    val authors: String="",
    val isbn: String="",
    val publisher: String="",
    val available: Int=0,
    val description: String="",
    val image: ImageBitmap? = null
)
