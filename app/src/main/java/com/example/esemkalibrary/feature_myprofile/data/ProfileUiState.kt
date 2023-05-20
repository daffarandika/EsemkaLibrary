package com.example.esemkalibrary.feature_myprofile.data

import androidx.compose.ui.graphics.ImageBitmap

data class ProfileUiState(
    val profilePhoto: ImageBitmap? = null,
    val name: String = "",
    val email: String = "",
    val borrowingHistory: List<CartItem> = emptyList()
)
