package com.example.esemkalibrary.core.model

import androidx.compose.ui.graphics.ImageBitmap

data class Book(
    val id: String,
    val name: String,
    val authors: String,
    val isbn: String,
    val publisher: String,
    val available: Int,
    val description: String,
    val image: ImageBitmap? = null,
)
