package com.example.esemkalibrary.core.model

import androidx.compose.ui.graphics.ImageBitmap

data class BookHeader(
    val id: String,
    val name: String,
    val authors: String,
    val image: ImageBitmap? = null,
)
