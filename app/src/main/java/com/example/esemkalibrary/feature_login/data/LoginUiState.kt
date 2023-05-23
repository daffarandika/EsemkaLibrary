package com.example.esemkalibrary.feature_login.data

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isEmailError: Boolean = false,
    val isPasswordError: Boolean = false,
    val isAllowedToLogin: Boolean = false,
    val token: String = ""
)
