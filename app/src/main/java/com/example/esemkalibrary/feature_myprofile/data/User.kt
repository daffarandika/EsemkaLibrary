package com.example.esemkalibrary.feature_myprofile.data

import androidx.compose.ui.graphics.ImageBitmap

data class User(
    val name: String = "",
    val email: String = "",
    val profilePhoto: ImageBitmap? = null
)
