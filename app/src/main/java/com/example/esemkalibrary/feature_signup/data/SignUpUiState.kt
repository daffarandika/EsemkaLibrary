package com.example.esemkalibrary.feature_signup.data

data class SignUpUiState(
    val name: String = "",
    val password: String= "",
    val cPassword: String= "",
    val email: String= "",
    val isPasswordVisible: Boolean = false,
    val isCPasswordVisible: Boolean= false,
    val isNameError: Boolean = false,
    val isPasswordError: Boolean= false,
    val isCPasswordError: Boolean= false,
    val isEmailError: Boolean= false,
)
