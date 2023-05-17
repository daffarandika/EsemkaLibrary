package com.example.esemkalibrary.feature_signup.ui

import androidx.lifecycle.ViewModel
import com.example.esemkalibrary.feature_signup.data.SignUpUiState
import kotlinx.coroutines.flow.*

class SignUpViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    fun updateNameInput(name: String) {
        _uiState.update {
            it.copy(name = name)
        }
    }
    fun updatePasswordInput(password: String) {
        _uiState.update {
            it.copy(password = password)
        }
    }
    fun updateCPasswordInput(cPassword: String) {
        _uiState.update {
            it.copy(cPassword = cPassword)
        }
    }
    fun updateEmailInput(email: String) {
        _uiState.update {
            it.copy(email = email)
        }
    }
    fun updatePasswordVisibility(isPasswordVisible: Boolean) {
        _uiState.update {
            it.copy(isPasswordVisible = isPasswordVisible)
        }
    }
    fun updateCPasswordVisibility(isCPasswordVisible: Boolean) {
        _uiState.update {
            it.copy(isCPasswordVisible = isCPasswordVisible)
        }
    }
    fun updateNameError(isNameError: Boolean) {
        _uiState.update {
            it.copy(isNameError = isNameError)
        }
    }
    fun updatePasswordError(isPasswordError: Boolean) {
        _uiState.update {
            it.copy(isPasswordError = isPasswordError)
        }
    }
    fun updateCPasswordError(isCPasswordError: Boolean) {
        _uiState.update {
            it.copy(isCPasswordError = isCPasswordError)
        }
    }
    fun updateEmailError(isEmailError: Boolean) {
        _uiState.update {
            it.copy(isEmailError = isEmailError)
        }
    }
    fun isEverythingFilled() = (uiState.value.name.isNotBlank() and uiState.value.password.isNotBlank() and uiState.value.cPassword.isNotBlank() and uiState.value.email.isNotBlank() )
    fun passwordsDoNotMatch() = (uiState.value.password != uiState.value.cPassword)
    fun isEmailValid() = (uiState.value.email.contains("@", true) and uiState.value.email.contains(".", true))
    fun isReadyToSignUp() = (isEverythingFilled() and (!passwordsDoNotMatch()))
}